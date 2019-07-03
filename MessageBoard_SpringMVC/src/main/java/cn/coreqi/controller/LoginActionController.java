package cn.coreqi.controller;

import cn.coreqi.constants.ValidateConstants;
import cn.coreqi.entities.ImageCode;
import cn.coreqi.entities.User;
import cn.coreqi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Connection;

@Controller
public class LoginActionController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login(String userName,String password,String imageCode,HttpSession session){
        ImageCode code = (ImageCode)session.getAttribute(ValidateConstants.SESSION_KEY);
        if(!code.getCode().equals(imageCode)){
            return "验证码错误！";
        }if(userName == null || password == null){
            return "用户名密码不能为空";
        }
        User user = userService.login(userName,password);
        if(user == null){
            return "用户名或者密码错误！";
        }
        return "登录成功，跳转";
    }
}
