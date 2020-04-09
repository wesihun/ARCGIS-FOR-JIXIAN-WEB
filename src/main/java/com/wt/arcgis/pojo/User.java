package com.wt.arcgis.pojo;

import java.util.Date;

public class User
{
   private int userid;
   private Role role;
   private Privilege privilege;
   private Department department;
   private Post post;//岗位
   private String username;
   private String password;
   private String realname;
   private String gender;
   private String telephonne;
   private Date createtime;
   private int state;

   public int getUserid() {
       return userid;
   }

   public void setUserid(int userid) {
       this.userid = userid;
   }

   public Role getRole() {
       return role;
   }

   public void setRole(Role role) {
       this.role = role;
   }

   public Privilege getPrivilege() {
       return privilege;
   }

   public void setPrivilege(Privilege privilege) {
       this.privilege = privilege;
   }

   public Department getDepartment() {
       return department;
   }

   public void setDepartment(Department department) {
       this.department = department;
   }

   public Post getPost() {
       return post;
   }

   public void setPost(Post post) {
       this.post = post;
   }

   public String getUsername() {
       return username;
   }

   public void setUsername(String username) {
       this.username = username;
   }

   public String getPassword() {
       return password;
   }

   public void setPassword(String password) {
       this.password = password;
   }

   public String getRealname() {
       return realname;
   }

   public void setRealname(String realname) {
       this.realname = realname;
   }

   public String getGender() {
       return gender;
   }

   public void setGender(String gender) {
       this.gender = gender;
   }

   public String getTelephonne() {
       return telephonne;
   }

   public void setTelephonne(String telephonne) {
       this.telephonne = telephonne;
   }

   public Date getCreatetime() {
       return createtime;
   }

   public void setCreatetime(Date createtime) {
       this.createtime = createtime;
   }

   public int getState() {
       return state;
   }

   public void setState(int state) {
       this.state = state;
   }

   
}
