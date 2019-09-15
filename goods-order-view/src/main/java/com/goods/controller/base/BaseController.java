package com.goods.controller.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

public class BaseController {
	
	public Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}

}
