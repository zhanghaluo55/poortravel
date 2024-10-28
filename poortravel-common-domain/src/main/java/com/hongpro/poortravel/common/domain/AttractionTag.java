package com.hongpro.poortravel.common.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "attraction_tag")
public class AttractionTag implements Serializable {
    private static final long serialVersionUID = 613168299076319453L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private Integer aid;

    private String label;

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

    /**
     * @return desc
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }
}