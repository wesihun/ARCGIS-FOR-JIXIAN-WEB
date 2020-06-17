package com.wt.arcgis.pojo;

import java.util.Date;
import java.util.List;

public class AnalysisMenue//统计分析菜单
{
    private int id;
    private int parentid;
    private String menuename;
    private String type;
    private Date createtime;
    private List<AnalysisMenue> subAnalysisMenue;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getParentid()
    {
        return parentid;
    }

    public void setParentid(int parentid)
    {
        this.parentid = parentid;
    }

    public String getMenuename()
    {
        return menuename;
    }

    public void setMenuename(String menuename)
    {
        this.menuename = menuename;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }

    public List<AnalysisMenue> getSubAnalysisMenue()
    {
        return subAnalysisMenue;
    }

    public void setSubAnalysisMenue(List<AnalysisMenue> subAnalysisMenue)
    {
        this.subAnalysisMenue = subAnalysisMenue;
    }
}
