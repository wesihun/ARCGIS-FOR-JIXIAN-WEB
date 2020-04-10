package com.wt.arcgis.controller;

import javax.servlet.http.HttpSession;

import com.wt.arcgis.mapper.UserMapper;
import com.wt.arcgis.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController
{
    @Autowired
    UserMapper userMapper;

    @RequestMapping("login")
    public String getUser(User user, HttpSession session)//登录
    {
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
    public User getUserInfo(HttpSession session){
        User user = (User)session.getAttribute("user");

        User resultUser = userMapper.getUserInfo(user);

        return resultUser;
    }






}
