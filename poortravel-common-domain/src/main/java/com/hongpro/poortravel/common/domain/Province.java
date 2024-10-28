package com.hongpro.poortravel.common.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "province")
public class Province implements Serializable {
    private static final long serialVersionUID = -7652548122131173770L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private String area;

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
     * @return area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area
     */
    public void setArea(String area) {
        this.area = area;
    }
}