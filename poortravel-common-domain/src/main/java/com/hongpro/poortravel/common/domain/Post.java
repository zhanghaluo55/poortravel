package com.hongpro.poortravel.common.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table(name = "post")
public class Post implements Serializable {
    private static final long serialVersionUID = 3824909377915961680L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private String title;

    private Integer uid;

    @Column(name = "post_image")
    private String postImage;

    @JSONField(format ="yyyy-MM-dd HH:mm:ss")
    @Column(name = "post_date")
    private Date postDate;

    @JSONField(format ="yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_date")
    private Date updateDate;

    private Integer likes;

    @Column(name = "share_num")
    private Integer shareNum;

    @Column(name = "comment_num")
    private Integer commentNum;

    private Integer status;

    private String province;

    private String content;

    private List<Tag> tags;

    private List<Files> files;

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public List<Files> getFiles() {
        return files;
    }

    public void setFiles(List<Files> files) {
        this.files = files;
    }

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
     * @return uid
     */
    public Integer getUid() {
        return uid;
    }

    /**
     * @param uid
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * @return post_image
     */
    public String getPostImage() {
        return postImage;
    }

    /**
     * @param postImage
     */
    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }

    /**
     * @return post_date
     */
    public Date getPostDate() {
        return postDate;
    }

    /**
     * @param postDate
     */
    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    /**
     * @return update_date
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * @param updateDate
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return likes
     */
    public Integer getLikes() {
        return likes;
    }

    /**
     * @param likes
     */
    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    /**
     * @return share_num
     */
    public Integer getShareNum() {
        return shareNum;
    }

    /**
     * @param shareNum
     */
    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    /**
     * @return comment_num
     */
    public Integer getCommentNum() {
        return commentNum;
    }

    /**
     * @param commentNum
     */
    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    /**
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province
     */
    public void setProvince(String province) {
        this.province = province;
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