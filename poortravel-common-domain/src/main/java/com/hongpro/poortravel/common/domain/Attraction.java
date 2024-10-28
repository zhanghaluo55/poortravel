package com.hongpro.poortravel.common.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Table(name = "attraction")
public class Attraction implements Serializable {
    private static final long serialVersionUID = -7450234117623647263L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;

    private String title;

    private String description;

    private String address;

    private String route;

    private String traffic;

    private String ticket;

    @Column(name = "operat_hours")
    private String operatHours;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "view_image")
    private String viewImage;

    @Column(name = "view_video")
    private String viewVideo;

    private Integer likes;

    private Integer grade;

    private Integer collect;

    private Integer status;

    private String latitude;

    private String longitude;

    private List<Files> files;

    private List<AttractionTag> attractionTags;

    public List<AttractionTag> getAttractionTags() {
        return attractionTags;
    }

    public void setAttractionTags(List<AttractionTag> attractionTags) {
        this.attractionTags = attractionTags;
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
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return route
     */
    public String getRoute() {
        return route;
    }

    /**
     * @param route
     */
    public void setRoute(String route) {
        this.route = route;
    }

    /**
     * @return traffic
     */
    public String getTraffic() {
        return traffic;
    }

    /**
     * @param traffic
     */
    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    /**
     * @return ticket
     */
    public String getTicket() {
        return ticket;
    }

    /**
     * @param ticket
     */
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    /**
     * @return operat_hours
     */
    public String getOperatHours() {
        return operatHours;
    }

    /**
     * @param operatHours
     */
    public void setOperatHours(String operatHours) {
        this.operatHours = operatHours;
    }

    /**
     * @return contact_phone
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    /**
     * @return view_image
     */
    public String getViewImage() {
        return viewImage;
    }

    /**
     * @param viewImage
     */
    public void setViewImage(String viewImage) {
        this.viewImage = viewImage;
    }

    /**
     * @return view_video
     */
    public String getViewVideo() {
        return viewVideo;
    }

    /**
     * @param viewVideo
     */
    public void setViewVideo(String viewVideo) {
        this.viewVideo = viewVideo;
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
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * @return collect
     */
    public Integer getCollect() {
        return collect;
    }

    /**
     * @param collect
     */
    public void setCollect(Integer collect) {
        this.collect = collect;
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
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}