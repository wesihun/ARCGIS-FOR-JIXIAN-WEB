package com.wt.arcgis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Value("${file_dir}")
    private String file_dir;

    public String getFile_dir() {
        return file_dir;
    }

    public void setFile_dir(final String file_dir) {
        this.file_dir = file_dir;
    }

    






}