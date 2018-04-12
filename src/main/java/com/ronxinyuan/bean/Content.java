package com.ronxinyuan.bean;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by 13045 on 2018/2/23.
 */
public class Content {

    private int id;
    private String title;
    private String url = "/plus/show";
    private String content;
    private String createTime;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @NotEmpty(message="标题不能为空")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return "/plus/show";
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", createTime='" + createTime + '\'' +
                ", status=" + status +
                '}';
    }
}
