package com.goods.shiro;

import com.goods.service.ow.OwUserService;
import com.goods.common.OwUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 自定义realm
 * 身份校验
 * @author gao
 *
 */
public class CustomRealm extends AuthorizingRealm {

	@Autowired
	private OwUserService owUserService;

	/**
	 * 授权
	 * @param principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(owUserService.findRolesCodeByUsername(username));
		authorizationInfo.setStringPermissions(owUserService.findPermissionsCodeByUsername(username));
		return authorizationInfo;
	}


	/**
	 * realm的认证方法，从数据库查询用户信息
	 * 认证信息.(身份验证) 
	 * Authentication 是用来验证用户身份 
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户的输入的账号.  
		String username = (String)token.getPrincipal();  
		//通过username从数据库中查找 User对象，如果找到，没找到.  
		//实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
		OwUser user  = owUserService.getOwUserByUsrename(username);

		if (user == null) {
			throw new UnknownAccountException();
		}

//        如果帐号锁定，输出
		if(Integer.parseInt(user.getState())==2){
			throw new DisabledAccountException();
		}

//        如果帐号未激活，输出
		if(Integer.parseInt(user.getState())==0){
			throw new LockedAccountException();
		}

		//加密方式;  
		//交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现  
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
				user.getUsername(),
				user.getPassword(),
				ByteSource.Util.bytes(user.getUsername() + user.getSalt()),
				getName());
		return authenticationInfo;
	}

	/**
	 *清除缓存，用户权限发生变化时进行调用
	 */
	public void clearCached() {
		PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
		super.clearCache(principals);
	}
	public static void main(String[] args) {
		String algorithmName = "md5";
		String username = "admin";
		String password = "andy123";
		String salt1 = username;
		String salt2 = new SecureRandomNumberGenerator().nextBytes().toHex();
		int hashIterations = 2;
		SimpleHash hash = new SimpleHash(algorithmName, password,
				salt1 + salt2, hashIterations);
		String encodedPassword = hash.toHex();
		System.out.println(encodedPassword);
		System.out.println(salt2);
	}

}
