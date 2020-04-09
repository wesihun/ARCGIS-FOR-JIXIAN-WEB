package com.wt.arcgis.pojo;

import java.util.Date;

public class Post {// 岗位
    private int postid;
    private String postname;
    private String postdetail;
    private Date createtime;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public String getPostname() {
        return postname;
    }

    public void setPostname(String postname) {
        this.postname = postname;
    }

    public String getPostdetail() {
        return postdetail;
    }

    public void setPostdetail(String postdetail) {
        this.postdetail = postdetail;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    

}