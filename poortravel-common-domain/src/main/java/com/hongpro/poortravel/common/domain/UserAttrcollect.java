package com.hongpro.poortravel.common.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "user_attrcollect")
public class UserAttrcollect implements Serializable {
    private static final long serialVersionUID = -4547546690428099991L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private Integer uid;

    private Integer aid;

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
     * @return aid
     */
    public Integer getAid() {
        return aid;
    }

    /**
     * @param aid
     */
    public void setAid(Integer aid) {
        this.aid = aid;
    }
}