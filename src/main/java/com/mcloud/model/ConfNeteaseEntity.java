package com.mcloud.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "conf_netease", schema = "cloud_secureStorage", catalog = "")
public class ConfNeteaseEntity {
    private int id;
    private String endPoint;
    private String secretId;
    private String secretKey;
    private String buckerName;
    private Integer userId;
    private String creator;
    private Timestamp createtime;
    private Timestamp updatetime;

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
    @Column(name = "secretId", nullable = true, length = 255)
    public String getSecretId() {
        return secretId;
    }

    public void setSecretId(String secretId) {
        this.secretId = secretId;
    }

    @Basic
    @Column(name = "secretKey", nullable = true, length = 255)
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Basic
    @Column(name = "buckerName", nullable = true, length = 255)
    public String getBuckerName() {
        return buckerName;
    }

    public void setBuckerName(String buckerName) {
        this.buckerName = buckerName;
    }

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
    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }

    @Basic
    @Column(name = "updatetime", nullable = true)
    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConfNeteaseEntity that = (ConfNeteaseEntity) o;
        return id == that.id &&
                Objects.equals(endPoint, that.endPoint) &&
                Objects.equals(secretId, that.secretId) &&
                Objects.equals(secretKey, that.secretKey) &&
                Objects.equals(buckerName, that.buckerName) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createtime, that.createtime) &&
                Objects.equals(updatetime, that.updatetime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, endPoint, secretId, secretKey, buckerName, userId, creator, createtime, updatetime);
    }
}
