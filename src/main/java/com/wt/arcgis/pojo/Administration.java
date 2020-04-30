package com.wt.arcgis.pojo;

import java.util.List;

public class Administration {//行政区
    private int id;
    private int ParentId;
    private String Name;
    private String TreeCode;
    private List<Administration> subAdministrations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTreeCode() {
        return TreeCode;
    }

    public void setTreeCode(String treeCode) {
        TreeCode = treeCode;
    }

    public List<Administration> getSubAdministrations() {
        return subAdministrations;
    }

    public void setSubAdministrations(List<Administration> subAdministrations) {
        this.subAdministrations = subAdministrations;
    }

    
}