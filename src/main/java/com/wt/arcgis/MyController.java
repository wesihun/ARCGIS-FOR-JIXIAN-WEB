package com.wt.arcgis;

import com.wt.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class MyController
{
    @Autowired
    UserMapper userMapper;


    @RequestMapping("getUser")
    @ResponseBody
    public String getUser()
    {
        User u = new User();
        u.setIdd(1);
        u.setReal_name("tom");


        //List<User> list = userMapper.queryAll(1, 2);
        List<User> list = userMapper.queryAll_2(u);

        for(int i=0; i<list.size(); i++)
        {
            User user = list.get(i);

            System.out.println(user.getIdd() + "," + user.getReal_name());
        }


        return "hello user";
    }




}
