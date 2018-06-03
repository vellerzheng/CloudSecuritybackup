package com.mcloud.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "conf_aliyun", schema = "cloud_secureStorage")
public class ConfAliyunEntity {
    private int id;
    private String endPoint;
    private String accessKey;
    private String accessKeySecret;
    private String bucketName;
    private String accessUrl;
    private Integer status;
    private Integer version;
    private Integer userId;
    private String creator;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "endPoint", nullable = true, length = 255)
    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    @Basic
    @Column(name = "accessKey", nullable = true, length = 255)
    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Basic
    @Column(name = "accessKeySecret", nullable = true, length = 255)
    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    @Basic
    @Column(name = "BucketName", nullable = true, length = 255)
    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Basic
    @Column(name = "accessUrl", nullable = true, length = 255)
    public String getAccessUrl() {
        return accessUrl;
    }

    public void setAccessUrl(String accessUrl) {
        this.accessUrl = accessUrl;
    }

    @Basic
    @Column(name = "status", nullable =  true)
    public Integer getStatus() { return  status; }

    public void setStatus(Integer status) { this.status = status; }

    @Basic
    @Column(name = "version", nullable = true)
    public Integer getVersion() { return  version; }

    public void setVersion(Integer version) { this.version = version; }

    @Basic
    @Column(name = "user_Id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "creator", nullable = true, length = 255)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Basic
    @Column(name = "createtime", nullable = true)
    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "updatetime", nullable = true)
    public Date getUpdatetime() { return updatetime; }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfAliyunEntity that = (ConfAliyunEntity) o;
        return id == that.id &&
                Objects.equals(endPoint, that.endPoint) &&
                Objects.equals(accessKey, that.accessKey) &&
                Objects.equals(accessKeySecret, that.accessKeySecret) &&
                Objects.equals(bucketName, that.bucketName) &&
                Objects.equals(accessUrl, that.accessUrl) &&
                Objects.equals(status, that.status) &&
                Objects.equals(version, that.version) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createtime, that.createtime) &&
                Objects.equals(updatetime, that.updatetime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, endPoint, accessKey, accessKeySecret, bucketName, accessUrl, status, version, userId, creator, createtime, updatetime);
    }
}
