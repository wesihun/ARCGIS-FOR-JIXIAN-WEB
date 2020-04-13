package com.wt.arcgis.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wt.arcgis.Config;
import com.wt.arcgis.mapper.MyMapper;
import com.wt.arcgis.pojo.Department;
import com.wt.arcgis.pojo.Menue;
import com.wt.arcgis.pojo.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MyController {
    @Autowired
    MyMapper myMapper;

    @Autowired
    Config config;

    @RequestMapping("login")
    public String getUser(final User user, final HttpSession session) {// 登录
        final User resultUser = myMapper.getUserByAccount(user);

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

    @RequestMapping("getUserInfo")
    public List<User> getUserInfo(final HttpSession session) {// 取得用户完整信息
        final User user = (User) session.getAttribute("user");

        final List<User> listUser = myMapper.getUserInfo(user);

        return listUser;
    }

    @RequestMapping(value = "getDepartment", produces = "application/json;charset=utf-8")
    public List<Department> getDepartment() {// 取得部门
        final List<Department> root = new ArrayList<Department>();

        final List<Department> listDepartment = myMapper.getRootDepartment();

        for (int i = 0; i < listDepartment.size(); i++) {
            final Department rootDepartment = listDepartment.get(i);

            rootDepartment.setSubDepartment(this.getSubDepartment(rootDepartment));

            root.add(rootDepartment);
        }

        return root;
    }

    public List<Department> getSubDepartment(final Department rootDepartment) {// 递归部门
        final List<Department> subDepartmentList = new ArrayList<Department>();
        final List<Department> subList = myMapper.getSubDepartment(rootDepartment.getDepartmentid());

        if (subList == null) {
            return subDepartmentList;
        } else {

            for (int i = 0; i < subList.size(); i++) {
                final Department subDepartment = subList.get(i);

                subDepartment.setSubDepartment(this.getSubDepartment(subDepartment));
                subDepartmentList.add(subDepartment);
            }
        }

        return subDepartmentList;
    }

    @RequestMapping(value = "getMenue", produces = "application/json;charset=utf-8")
    public List<Menue> getMenue() {// 取得菜单
        final List<Menue> root = new ArrayList<Menue>();

        final List<Menue> listMenue = myMapper.getRootMenue();

        for (int i = 0; i < listMenue.size(); i++) {
            final Menue rootMenue = listMenue.get(i);

            rootMenue.setSubMenue(this.getSubMenue(rootMenue));

            root.add(rootMenue);
        }

        return root;
    }

    public List<Menue> getSubMenue(final Menue rootMenue) {// 递归菜单
        final List<Menue> subMenueList = new ArrayList<Menue>();
        final List<Menue> subList = myMapper.getSubMenue(rootMenue.getMenueid());

        if (subList == null) {
            return null;
        } else {

            for (int i = 0; i < subList.size(); i++) {
                final Menue subMenue = subList.get(i);

                subMenue.setSubMenue(this.getSubMenue(subMenue));
                subMenueList.add(subMenue);
            }
        }

        return subMenueList;
    }

    @RequestMapping(value = "upload", produces = "application/json;charset=utf-8") // 单文件上传
    public String upload(@RequestParam("file") final MultipartFile multipartFile)
            throws IllegalStateException, IOException {
        if (multipartFile.isEmpty()) {
            return "no file";
        }

        final double MB = multipartFile.getSize() / 1024 / 1024.0;
        System.out.println("MB:" + MB);

        final String folder = config.getFile_dir();

        final String upFileName = multipartFile.getOriginalFilename();

        final String path = folder + upFileName;

        final File myFile = new File(path);

        if (!myFile.getParentFile().exists()) {
            myFile.getParentFile().mkdirs();
        }

        multipartFile.transferTo(myFile);

        return path;
    }

    @RequestMapping(value = "uploadMulty", produces = "application/json;charset=utf-8")
    public String uploadMulty(@RequestParam("file") final MultipartFile[] multipartFile)
            throws IllegalStateException, IOException {// 多文件上传

        final String folder = config.getFile_dir();

        for (int i = 0; i < multipartFile.length; i++) {
            final MultipartFile sigleFile = multipartFile[i];

            if (sigleFile.isEmpty()) {
                return "no single file";
            }

            final double MB = sigleFile.getSize() / 1024 / 1024.0;
            System.out.println("MB:" + MB);

            final String upFileName = sigleFile.getOriginalFilename();
            final String path = folder + upFileName;
            final File myFile = new File(path);

            if (!myFile.getParentFile().exists()) {
                myFile.getParentFile().mkdirs();
            }

            sigleFile.transferTo(myFile);

        }

        return folder;
    }

    @RequestMapping("download")
    public void download(final HttpServletResponse response) throws IOException {//下载

        String requestFileName  = "超详细Oracle教程.pdf";
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
        final OutputStream outputStream = response.getOutputStream();
        final byte[] buff = new byte[1024];
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




}
