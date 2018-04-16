package com.mcloud.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "conf_qiniu", schema = "cloud_secureStorage", catalog = "")
public class ConfQiniuEntity {
    private int id;
    private String domainOfBucket;
    private String accessKey;
    private String secretKey;
    private String bucket;
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
    @Column(name = "domainOfBucket", nullable = true, length = 255)
    public String getDomainOfBucket() {
        return domainOfBucket;
    }

    public void setDomainOfBucket(String domainOfBucket) {
        this.domainOfBucket = domainOfBucket;
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
    @Column(name = "secretKey", nullable = true, length = 255)
    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Basic
    @Column(name = "bucket", nullable = true, length = 255)
    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
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
        ConfQiniuEntity that = (ConfQiniuEntity) o;
        return id == that.id &&
                Objects.equals(domainOfBucket, that.domainOfBucket) &&
                Objects.equals(accessKey, that.accessKey) &&
                Objects.equals(secretKey, that.secretKey) &&
                Objects.equals(bucket, that.bucket) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(creator, that.creator) &&
                Objects.equals(createtime, that.createtime) &&
                Objects.equals(updatetime, that.updatetime);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, domainOfBucket, accessKey, secretKey, bucket, userId, creator, createtime, updatetime);
    }
}
