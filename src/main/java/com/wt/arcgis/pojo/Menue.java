package com.wt.arcgis.pojo;

import java.util.Date;

public class Menue {
    private int menueid;
    private String menuename;
    private int parentmenueid;
    private Date createtime;

    public int getMenueid() {
        return menueid;
    }

    public void setMenueid(int menueid) {
        this.menueid = menueid;
    }

    public String getMenuename() {
        return menuename;
    }

    public void setMenuename(String menuename) {
        this.menuename = menuename;
    }

    public int getParentmenueid() {
        return parentmenueid;
    }

    public void setParentmenueid(int parentmenueid) {
        this.parentmenueid = parentmenueid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    
}