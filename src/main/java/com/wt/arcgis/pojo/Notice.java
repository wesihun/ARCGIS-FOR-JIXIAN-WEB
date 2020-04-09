package com.wt.arcgis.pojo;

import java.util.Date;

public class Notice {
    private int noticeid;
    private String content;
    private String title;
    private String image;
    private String titleimage;
    private int istitle;
    private Date createtime;

    public int getNoticeid() {
        return noticeid;
    }

    public void setNoticeid(int noticeid) {
        this.noticeid = noticeid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitleimage() {
        return titleimage;
    }

    public void setTitleimage(String titleimage) {
        this.titleimage = titleimage;
    }

    public int getIstitle() {
        return istitle;
    }

    public void setIstitle(int istitle) {
        this.istitle = istitle;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    

}