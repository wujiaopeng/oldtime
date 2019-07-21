package com.novel.oldtime.controller;

import com.novel.oldtime.domain.User;
import com.novel.oldtime.service.UserService;
import com.novel.oldtime.utils.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: Peng
 * @Date: 2019/5/14 16:21
 * @Description:
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @Resource
    private UserService userService;

    /*
        web端请求
     */
    @RequestMapping("/")    //进入系统默认页面
    public String toIndex(){
        return "login";
    }
    @RequestMapping("/main")    //去到主页
    public String toMain1(){
        return "main";
    }
    @RequestMapping("/toLogin")    //退出 去到登录页
    public String toLogin(HttpSession session){
        session.removeAttribute(Constants.ADMIN_USER_SESSION);  //清除管理员用户session
        return "login";
    }
    @RequestMapping("/doLogin")    //登录请求
    public String doLogin(HttpSession session,
                          HttpServletRequest request,
                          @RequestParam(value="code",required=false) String code,
                          @RequestParam(value="password",required=false) String password){

        User user = userService.getUserByCode(code);
        if(user != null && user.getCode().equals("admin")){
            if(password.equals(user.getPassword())){
                user.setPassword("");
                session.setAttribute(Constants.ADMIN_USER_SESSION,user);
                return "index";
            }
            else{
                String error = "密码输入错误";
                request.setAttribute("error",error);
                return "login";
            }
        }
        else {
            String error = "该用户不存在";
            request.setAttribute("error",error);
            return "login";
        }
    }

    /*
        安卓端请求
     */
    @RequestMapping("/main.json")
    public String toMain(){
        return "main";
    }

}
