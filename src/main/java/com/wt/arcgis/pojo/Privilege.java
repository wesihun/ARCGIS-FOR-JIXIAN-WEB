package com.wt.arcgis.pojo;

import java.util.Date;

public class Privilege {

    private int privilegeid;
    private String privilegecode;
    private String privilegename;
    private Date createtime;

    public int getPrivilegeid() {
        return privilegeid;
    }

    public void setPrivilegeid(int privilegeid) {
        this.privilegeid = privilegeid;
    }

    public String getPrivilegecode() {
        return privilegecode;
    }

    public void setPrivilegecode(String privilegecode) {
        this.privilegecode = privilegecode;
    }

    public String getPrivilegename() {
        return privilegename;
    }

    public void setPrivilegename(String privilegename) {
        this.privilegename = privilegename;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    
}