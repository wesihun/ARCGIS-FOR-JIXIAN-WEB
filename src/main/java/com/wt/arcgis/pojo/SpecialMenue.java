package com.wt.arcgis.pojo;

import java.util.Date;
import java.util.List;

public class SpecialMenue//装箱调查菜单
{
    private int id;
    private int parentid;
    private String menuename;
    private String serverpath;
    private Date createtime;
    private List<SpecialMenue> subSpecialMenue;

    public List<SpecialMenue> getSubSpecialMenue()
    {
        return subSpecialMenue;
    }

    public void setSubSpecialMenue(List<SpecialMenue> subSpecialMenue)
    {
        this.subSpecialMenue = subSpecialMenue;
    }

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

    public String getServerpath()
    {
        return serverpath;
    }

    public void setServerpath(String serverpath)
    {
        this.serverpath = serverpath;
    }

    public Date getCreatetime()
    {
        return createtime;
    }

    public void setCreatetime(Date createtime)
    {
        this.createtime = createtime;
    }
}
