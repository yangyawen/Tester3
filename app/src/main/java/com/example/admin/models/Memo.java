package com.example.admin.models;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2016/3/22.
 */
@DatabaseTable(tableName = "memos")
public class Memo {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(useGetSet = true)
    private String title;
    @DatabaseField
    private String content;
    @DatabaseField
    private String time;

    //A no-argument constructor is needed so the object can be returned by a query.
    public Memo(){}

    public Memo(String title, String content){
        this.title = title;
        this.content = content;

        //插入当前时间
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.time = dateFormat.format(new Date());

    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getContent(){
        return this.content;
    }

    public void setContent(String content){
        this.content = content;
    }

    public String getTime(){
        return this.time;
    }

    public void setTime(String time){
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = ").append(id);
        sb.append(", ").append("title = ").append(title);
        sb.append(", ").append("content = ").append(content);
        sb.append(", ").append("time = ").append(time);
        return sb.toString();
    }
}
