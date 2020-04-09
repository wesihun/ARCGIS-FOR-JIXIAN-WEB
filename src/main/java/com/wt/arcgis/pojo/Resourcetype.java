package com.wt.arcgis.pojo;

import java.util.Date;

public class Resourcetype {
    private int resourcetypeid;
    private String resourcetype;
    private Date createtime;

    public int getResourcetypeid() {
        return resourcetypeid;
    }

    public void setResourcetypeid(int resourcetypeid) {
        this.resourcetypeid = resourcetypeid;
    }

    public String getResourcetype() {
        return resourcetype;
    }

    public void setResourcetype(String resourcetype) {
        this.resourcetype = resourcetype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    
}