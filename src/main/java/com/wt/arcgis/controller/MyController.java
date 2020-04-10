package com.wt.arcgis.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.wt.arcgis.mapper.UserMapper;
import com.wt.arcgis.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController{
    @Autowired
    UserMapper userMapper;

    @RequestMapping("login")
    public String getUser(User user, HttpSession session){//登录
        User resultUser = userMapper.getUserByAccount(user);

        String json = "{" + '"' + "result" + '"' + ":" + '"' + "fail" + '"' + "}";

        if(null == resultUser){
            return json;
        }else{
            session.setAttribute("user", resultUser);
            session.setMaxInactiveInterval(60 * 30);
            json = "{" + '"' + "result" + '"' + ":" + '"' + "success" + '"' + "}";
            return json;
        }

    }

    @RequestMapping("getUserInfo")
    public List<User> getUserInfo(HttpSession session){//取得用户完整信息
        User user = (User)session.getAttribute("user");

        List<User> listUser = userMapper.getUserInfo(user);

        return listUser;
    }






}
