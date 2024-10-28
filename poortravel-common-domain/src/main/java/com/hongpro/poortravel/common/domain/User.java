package com.hongpro.poortravel.common.domain;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = -5517590794272015855L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private String username;

    private String password;

    private String airphone;

    private String email;

    private Integer gender;

    private String nickname;

    @JSONField(format ="yyyy-MM-dd HH:mm:ss")
    @Column(name = "reg_date")
    private Date regDate;

    @Column(name = "head_image")
    private String headImage;

    private String address;

    private Integer status;

    private Integer usertype;

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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return airphone
     */
    public String getAirphone() {
        return airphone;
    }

    /**
     * @param airphone
     */
    public void setAirphone(String airphone) {
        this.airphone = airphone;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return gender
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return reg_date
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * @param regDate
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * @return head_image
     */
    public String getHeadImage() {
        return headImage;
    }

    /**
     * @param headImage
     */
    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
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
     * @return usertype
     */
    public Integer getUsertype() {
        return usertype;
    }

    /**
     * @param usertype
     */
    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }
}