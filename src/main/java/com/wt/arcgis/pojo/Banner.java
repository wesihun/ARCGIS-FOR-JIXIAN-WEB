package com.wt.arcgis.pojo;

import java.util.Date;

public class Banner {
    private int bannerid;
    private String bannerdir;
    private int state;
    private Date createtime;

    public int getBannerid() {
        return bannerid;
    }

    public void setBannerid(int bannerid) {
        this.bannerid = bannerid;
    }

    public String getBannerdir() {
        return bannerdir;
    }

    public void setBannerdir(String bannerdir) {
        this.bannerdir = bannerdir;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    
}