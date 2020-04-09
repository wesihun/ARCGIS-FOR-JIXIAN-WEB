package com.wt.arcgis.pojo;

import java.util.Date;

public class Resource {
    private int resourceid;
    private Resourcetype resourcetype;
    private String resourcedir;
    private String resourcename;
    private String sender;//发布机构
    private Date createtime;
    private float filesize;

    public int getResourceid() {
        return resourceid;
    }

    public void setResourceid(int resourceid) {
        this.resourceid = resourceid;
    }

    public Resourcetype getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(Resourcetype resourcetype) {
        this.resourcetype = resourcetype;
    }

    public String getResourcedir() {
        return resourcedir;
    }

    public void setResourcedir(String resourcedir) {
        this.resourcedir = resourcedir;
    }

    public String getResourcename() {
        return resourcename;
    }

    public void setResourcename(String resourcename) {
        this.resourcename = resourcename;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public float getFilesize() {
        return filesize;
    }

    public void setFilesize(float filesize) {
        this.filesize = filesize;
    }

    

}