package com.cumt.mynotes;

/**
 * 用于暂时存储数据
 */

public class Store {
    private String title;//标题
    private String content;//内容
    private String time;//时间
    private int id;//编号
    public Store(int id,String title,String content ,String time){
        this.id=id;
        this.title=title;
        this.content=content;
        this.time=time;
    }
    public Store(String title,String content,String time){
        this.title=title;
        this.content=content;
        this.time=time;
    }
    public Store(int id,String title,String time){
        this.id=id;
        this.title=title;
        this.time=time;
    }
    public Store(String title,String content){
        this.title=title;
        this.content=content;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getContent() {
        return content;
    }
    public String getTime() {
        return time;
    }

}
