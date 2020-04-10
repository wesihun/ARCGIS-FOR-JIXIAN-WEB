package com.wt.arcgis.pojo;

import java.util.Date;
import java.util.List;

public class Department{
    private int departmentid;
    private String departmentname;
    private int parentid;
    private Date createtime;
    private List<Department> subDepartment;
    

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public List<Department> getSubDepartment() {
        return subDepartment;
    }

    public void setSubDepartment(List<Department> subDepartment) {
        this.subDepartment = subDepartment;
    }
    
    

}