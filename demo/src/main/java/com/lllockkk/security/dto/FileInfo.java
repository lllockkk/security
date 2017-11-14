package com.lllockkk.security.dto;

/**
 * Created by L on 2017/8/21.
 */
public class FileInfo {
    private String filepath;

    public FileInfo(String filepath) {
        this.filepath = filepath;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }
}
