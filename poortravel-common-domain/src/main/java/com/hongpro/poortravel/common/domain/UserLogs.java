package com.hongpro.poortravel.common.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user_logs")
public class UserLogs implements Serializable {
    private static final long serialVersionUID = 7676295532287763492L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;
    @JSONField(format ="yyyy-MM-dd HH:mm:ss")
    private Date time;

    private String type;

    @Column(name = "user_id")
    private Integer userId;

    private String content;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }
}