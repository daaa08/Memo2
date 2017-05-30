package com.example.da08.memo2.domain;

/**
 * Created by Da08 on 2017. 5. 30..
 */

public class Memo {
    private String id;
    private String content;
    private String date;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
