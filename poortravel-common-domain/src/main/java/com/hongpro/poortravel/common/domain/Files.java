package com.hongpro.poortravel.common.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "files")
public class Files implements Serializable {
    private static final long serialVersionUID = -3995591468751028796L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private String path;

    private String filename;

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
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return filename
     */
    public String getFilename() {
        return filename;
    }

    /**
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
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