package com.hongpro.poortravel.common.domain;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "post_comment_reply")
public class PostCommentReply implements Serializable {
    private static final long serialVersionUID = -8768978309125224252L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    @Column(name = "pp_pid")
    private Integer ppPid;

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
     * @return pp_pid
     */
    public Integer getPpPid() {
        return ppPid;
    }

    /**
     * @param ppPid
     */
    public void setPpPid(Integer ppPid) {
        this.ppPid = ppPid;
    }
}