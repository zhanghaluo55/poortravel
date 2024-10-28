package com.hongpro.poortravel.common.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "news")
public class News implements Serializable {
    private static final long serialVersionUID = -5249359851910468750L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private String title;

    private String writer;

    @Column(name = "news_image")
    private String newsImage;

    @JSONField(format ="yyyy-MM-dd HH:mm:ss")
    @Column(name = "news_date")
    private Date newsDate;

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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return writer
     */
    public String getWriter() {
        return writer;
    }

    /**
     * @param writer
     */
    public void setWriter(String writer) {
        this.writer = writer;
    }

    /**
     * @return news_image
     */
    public String getNewsImage() {
        return newsImage;
    }

    /**
     * @param newsImage
     */
    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    /**
     * @return news_date
     */
    public Date getNewsDate() {
        return newsDate;
    }

    /**
     * @param newsDate
     */
    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
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