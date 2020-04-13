package com.wt.arcgis.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.wt.arcgis.mapper.MyMapper;
import com.wt.arcgis.pojo.Department;
import com.wt.arcgis.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MyController{
    @Autowired
    MyMapper myMapper;

    @RequestMapping("login")
    public String getUser(User user, HttpSession session){//登录
        User resultUser = myMapper.getUserByAccount(user);

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

        List<User> listUser = myMapper.getUserInfo(user);

        return listUser;
    }


    @RequestMapping(value="getDepartment", produces="application/json;charset=utf-8")
    public List<Department> getDepartment(){//取得部门
        List<Department> root =  new ArrayList<Department>();

        List<Department> listDepartment =  myMapper.getRootDepartment();

        for(int i=0; i<listDepartment.size(); i++){
            Department rootDepartment = listDepartment.get(i);

            rootDepartment.setSubDepartment(this.getSubDepartment(rootDepartment));

            root.add(rootDepartment);
        }  

        return root;
    }

    public List<Department> getSubDepartment(Department rootDepartment){//递归部门
        List<Department> subDepartmentList = new ArrayList<Department>();
        List<Department> subList = myMapper.getSubDepartment(rootDepartment.getDepartmentid());
       
        if(subList == null){
            return subDepartmentList;
        }else{
           
            for(int i=0; i<subList.size(); i++){
                Department subDepartment = subList.get(i);
               
                subDepartment.setSubDepartment(this.getSubDepartment(subDepartment));
                subDepartmentList.add(subDepartment);
            }
        }

        return subDepartmentList;
    }




}
