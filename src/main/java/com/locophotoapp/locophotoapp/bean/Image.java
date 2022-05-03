package com.locophotoapp.locophotoapp.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Optional;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="lat")
    private Double lat;

    @Column(name="lng")
    private Double lng;

    @Column(name="city")
    private String city;

    @Column(name="userID")
    private String userID;

    @Column(name="date")
    private String date;

    @Column(name="views")
    private Integer views;

    @Column(name="likes")
    private Integer likes;

    @Column(name="url")
    private String url;

    @Column(name="comment")
    private String comment;

    @Column(name="deleted")
    private Boolean deleted;

    public long getId() {
        return id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void increaseViews() {
        setViews(Optional.ofNullable(views).orElse(0) +1);
    }

    public void increaseLikes() {
        setLikes(Optional.ofNullable(likes).orElse(0) +1);
    }

    public void decreaseLikes() {
        setLikes(Optional.ofNullable(likes).orElse(0) -1);
    }

}
