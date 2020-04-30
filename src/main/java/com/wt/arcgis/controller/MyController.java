package com.wt.arcgis.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wt.arcgis.Config;
import com.wt.arcgis.mapper.MyMapper;
import com.wt.arcgis.pojo.Administration;
import com.wt.arcgis.pojo.Department;
import com.wt.arcgis.pojo.Menue;
import com.wt.arcgis.pojo.Post;
import com.wt.arcgis.pojo.Role;
import com.wt.arcgis.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials="true")
public class MyController {
    @Autowired
    MyMapper myMapper;

    @Autowired
    Config config;

    @RequestMapping(value="login", produces = "application/json;charset=utf-8")
    public String getUser(User user,  HttpSession session) {// 登录
        User resultUser = myMapper.getUserByAccount(user);

        String json = "{" + '"' + "result" + '"' + ":" + '"' + "fail" + '"' + "}";

        if (null == resultUser) {
            return json;
        } else {
            session.setAttribute("user", resultUser);
            session.setMaxInactiveInterval(60 * 30);
            json = "{" + '"' + "result" + '"' + ":" + '"' + "success" + '"' + "}";


            return json;
        }

    }

    @RequestMapping(value = "getUserInfo", produces = "application/json;charset=utf-8")
    public List<User> getUserInfo(HttpSession session) {// 取得用户完整信息
        User user = (User) session.getAttribute("user");

        List<User> listUser = myMapper.getUserInfo(user);

        return listUser;
    }

    @RequestMapping(value = "getDepartment", produces = "application/json;charset=utf-8")
    public List<Department> getDepartment() {// 取得部门
        List<Department> root = new ArrayList<Department>();

        List<Department> listDepartment = myMapper.getRootDepartment();

        for (int i = 0; i < listDepartment.size(); i++) {
            Department rootDepartment = listDepartment.get(i);

            rootDepartment.setSubDepartment(this.getSubDepartment(rootDepartment));

            root.add(rootDepartment);
        }

        return root;
    }

    public List<Department> getSubDepartment(Department rootDepartment) {// 递归部门
        List<Department> subDepartmentList = new ArrayList<Department>();
        List<Department> subList = myMapper.getSubDepartment(rootDepartment.getDepartmentid());

        if (subList == null) {
            return subDepartmentList;
        } else {

            for (int i = 0; i < subList.size(); i++) {
                Department subDepartment = subList.get(i);

                subDepartment.setSubDepartment(this.getSubDepartment(subDepartment));
                subDepartmentList.add(subDepartment);
            }
        }

        return subDepartmentList;
    }

    @RequestMapping(value = "getMenue", produces = "application/json;charset=utf-8")
    public List<Menue> getMenue() {// 取得菜单
        List<Menue> root = new ArrayList<Menue>();

        List<Menue> listMenue = myMapper.getRootMenue();

        for (int i = 0; i < listMenue.size(); i++) {
            Menue rootMenue = listMenue.get(i);

            rootMenue.setSubMenue(this.getSubMenue(rootMenue));

            root.add(rootMenue);
        }

        return root;
    }

    public List<Menue> getSubMenue(Menue rootMenue) {// 递归菜单
        List<Menue> subMenueList = new ArrayList<Menue>();
        List<Menue> subList = myMapper.getSubMenue(rootMenue.getMenueid());

        if (subList == null) {
            return null;
        } else {

            for (int i = 0; i < subList.size(); i++) {
                Menue subMenue = subList.get(i);

                subMenue.setSubMenue(this.getSubMenue(subMenue));
                subMenueList.add(subMenue);
            }
        }

        return subMenueList;
    }

    @RequestMapping(value = "upload", produces = "application/json;charset=utf-8") // 单文件上传
    public String upload(@RequestParam("file") MultipartFile multipartFile)
            throws IllegalStateException, IOException {
        if (multipartFile.isEmpty()) {
            return "no file";
        }

        double MB = multipartFile.getSize() / 1024 / 1024.0;
        System.out.println("MB:" + MB);

        String folder = config.getFile_dir();

        String upFileName = multipartFile.getOriginalFilename();

        String path = folder + upFileName;

        File myFile = new File(path);

        if (!myFile.getParentFile().exists()) {
            myFile.getParentFile().mkdirs();
        }

        multipartFile.transferTo(myFile);

        return path;
    }

    @RequestMapping(value = "uploadMulty", produces = "application/json;charset=utf-8")
    public String uploadMulty(@RequestParam("file") MultipartFile[] multipartFile)
            throws IllegalStateException, IOException {// 多文件上传

        String folder = config.getFile_dir();

        for (int i = 0; i < multipartFile.length; i++) {
            MultipartFile sigleFile = multipartFile[i];

            if (sigleFile.isEmpty()) {
                return "no single file";
            }

            double MB = sigleFile.getSize() / 1024 / 1024.0;
            System.out.println("MB:" + MB);

            String upFileName = sigleFile.getOriginalFilename();
            String path = folder + upFileName;
            File myFile = new File(path);

            if (!myFile.getParentFile().exists()) {
                myFile.getParentFile().mkdirs();
            }

            sigleFile.transferTo(myFile);

        }

        return folder;
    }

    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {//下载

        String requestFileName = "超详细Oracle教程.pdf";
        String filename = config.getFile_dir() + requestFileName;

         // 设置信息给客户端不解析
         String type = new MimetypesFileTypeMap().getContentType(filename);
         // 设置contenttype，即告诉客户端所发送的数据属于什么类型
         response.setHeader("Content-type",type);
         // 设置编码
         String hehe = new String(requestFileName.getBytes("utf-8"), "iso-8859-1");
         // 设置扩展头，当Content-Type 的类型为要下载的类型时 , 这个信息头会告诉浏览器这个文件的名字和类型。
         response.setHeader("Content-Disposition", "attachment;filename=" + hehe);

        // 发送给客户端的数据
         OutputStream outputStream = response.getOutputStream();
         byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File(filename)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }


    @RequestMapping("getRole")
    public List<Role> getRole(){//角色

        List<Role> roleList =  myMapper.getRole();

        return roleList;
    }

    @RequestMapping("getPost")
    public List<Post> getPost(){//岗位
        List<Post> postList = myMapper.getPost();

        return postList;
    }
  
    @RequestMapping("regist")
    @Transactional(rollbackFor=Exception.class)
    public String regist(User user) throws RuntimeException {//注册

        String json = "{" + '"' + "result" + '"' + ":" + '"' + "success" + '"' + "}";
        User checkUser = myMapper.getUserByAccountNoState(user); //账户重复
        if(null !=checkUser){
            json = "{" + '"' + "result" + '"' + ":" + '"' + "repeat" + '"' + "}";
            return json;
        }

        user.setCreatetime(new Date());

        try{
            myMapper.insertUser(user);
            User savedUser = myMapper.getUserByAccountNoState(user);
            user.setUserid(savedUser.getUserid());

            myMapper.insertUserRole(user);
        }catch(Exception e){
            e.printStackTrace();
            json = "{" + '"' + "result" + '"' + ":" + '"' + "fail" + '"' + "}";
            throw new RuntimeException();
        }
        finally{
            return json;
        }
    }

    @RequestMapping(value = "getSecondCategory", produces = "application/json;charset=utf-8")
    public List<Menue> getSecondCategory(Menue menue){//取得目录树点击的二级地类编码
        List<Menue> menueList = this.getSubMenue(menue);

        return menueList;
    }

    @RequestMapping(value = "getMenueByMenueId", produces = "application/json;charset=utf-8")
    public Menue getMenueByMenueId(Menue menue){//根据menueid取得menue
        Menue resultMenue = myMapper.getMenueByMenueId(menue);

        return resultMenue;
    }


    @RequestMapping(value = "getAdministration", produces = "application/json;charset=utf-8")
    public List<Administration> getAdministration() {// 取得行政区
        List<Administration> root = new ArrayList<Administration>();

        List<Administration> listAdministrations = myMapper.getRootAdministration();

        for (int i = 0; i < listAdministrations.size(); i++) {
            Administration rootAdministration = listAdministrations.get(i);

            rootAdministration.setSubAdministrations(this.getSubAdministration(rootAdministration));

            root.add(rootAdministration);
        }

        return root;
    }

    public List<Administration> getSubAdministration(Administration administration) {//递归行政区
        List<Administration> subAdministrationList = new ArrayList<Administration>();
        List<Administration> subList = myMapper.getSubAdministrations(administration.getId());

        if (subList == null) {
            return null;
        } else {

            for (int i = 0; i < subList.size(); i++) {
                Administration subAdministration = subList.get(i);

                subAdministration.setSubAdministrations(this.getSubAdministration(subAdministration));
                subAdministrationList.add(subAdministration);
            }
        }

        return subAdministrationList;
    }






}
