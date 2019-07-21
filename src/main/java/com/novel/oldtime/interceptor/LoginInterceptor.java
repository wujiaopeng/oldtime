package com.novel.oldtime.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.novel.oldtime.domain.User;
import com.novel.oldtime.utils.Constants;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        //登录判断，判断session里面是否有user
        boolean flag=false;
        
    	User user=(User)request.getSession().getAttribute(Constants.ADMIN_USER_SESSION);
    	if(user != null){
    		flag = true;
        }
        else {
            response.sendRedirect(request.getContextPath()+"/toLogin");
        }

        return flag;
    }
	
}
