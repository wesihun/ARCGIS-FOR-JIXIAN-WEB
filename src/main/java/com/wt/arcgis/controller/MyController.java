package com.wt.arcgis.controller;

import com.wt.arcgis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController
{
    @Autowired
    UserMapper userMapper;

    @RequestMapping("login")
    public String getUser()
    {
       
        return "hello user";
    }




}
