package com.hongpro.poortravel.common.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "post_files")
public class PostFiles implements Serializable {
    private static final long serialVersionUID = 6744337346569801764L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private Integer pid;

    private Integer fid;

    private String filetype;

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
     * @return pid
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * @param pid
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * @return fid
     */
    public Integer getFid() {
        return fid;
    }

    /**
     * @param fid
     */
    public void setFid(Integer fid) {
        this.fid = fid;
    }

    /**
     * @return filetype
     */
    public String getFiletype() {
        return filetype;
    }

    /**
     * @param filetype
     */
    public void setFiletype(String filetype) {
        this.filetype = filetype;
    }
}