package com.wt.arcgis.pojo;

import java.util.Date;
import java.util.List;

public class Menue {
    private int menueid;
    private String menuename;
    private int parentmenueid;
    private String firstcategory;
    private String secondcategory;
    private List<Menue> subMenue;
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

    public List<Menue> getSubMenue() {
        return subMenue;
    }

    public void setSubMenue(List<Menue> subMenue) {
        this.subMenue = subMenue;
    }

    public String getFirstcategory() {
        return firstcategory;
    }

    public void setFirstcategory(String firstcategory) {
        this.firstcategory = firstcategory;
    }

    public String getSecondcategory() {
        return secondcategory;
    }

    public void setSecondcategory(String secondcategory) {
        this.secondcategory = secondcategory;
    }

    
}