package com.goods.controller.sys;

import com.goods.config.WebConfig;
import com.goods.controller.base.BaseController;
import com.goods.model.Result;
import com.goods.service.ow.OwUserService;
import com.goods.common.ow.OwUser;
import com.goods.util.ResultUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class SysController extends BaseController{
    @Autowired
    private OwUserService owUserService;
    @PostMapping("ajaxLogin")
    public Result<OwUser> submitLogin(OwUser owUser) {
        Result<OwUser> ret = null;
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(owUser.getUsername(), owUser.getPassword());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken); // 完成登录
            String username = (String) subject.getPrincipal();
            OwUser Activeuser = owUserService.getOwUserByUsrename(username);
            subject.getSession().setAttribute(WebConfig.ActiveUser, Activeuser);
            ret = ResultUtil.success(Activeuser);
        } catch (Exception exception) {
            if (exception instanceof UnknownAccountException) {
                ret = ResultUtil.error(1, "账号不存在!");
            } else if (exception instanceof IncorrectCredentialsException) {
                ret = ResultUtil.error(2, "密码不正确!");
            } else if (exception instanceof LockedAccountException) {
                ret = ResultUtil.error(3, "用户被锁定,请联系管理员!");
            } else if (exception instanceof DisabledAccountException) {
                ret = ResultUtil.error(3, "用户被禁用,请联系管理员!");
            } else if (exception instanceof ExcessiveAttemptsException) {
                ret = ResultUtil.error(3, "密码尝试次数超限，用户已锁定!");
            } else {
                exception.printStackTrace();
                ret = ResultUtil.error(4, "登录失败，发生未知错误：" + exception);
            }
        }
        return ret;
    }
    @RequestMapping(value = "login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    @RequestMapping(value = "index")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        OwUser user = (OwUser) getSession().getAttribute(WebConfig.ActiveUser);
        modelAndView.addObject("username", user.getUsername());
        return modelAndView;
    }
    @RequestMapping("/{parameter}")
    public ModelAndView showView(@PathVariable String parameter){
        return new ModelAndView(parameter);
    }
}
