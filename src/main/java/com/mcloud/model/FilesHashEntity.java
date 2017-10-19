package com.mcloud.model;

import javax.persistence.*;

/**
 * Created by vellerzheng on 2017/10/19.
 */
@Entity
@Table(name = "files_hash", schema = "cloud_secureStorage", catalog = "")
public class FilesHashEntity {
    private int id;
    private String fileHash;
    private String aliyunHash;
    private String neteaseHash;
    private String qcloudHash;
    private String qiniuHash;
    private String upyunHash;
    private FilesEntity filesIdByFileId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "file_hash", nullable = true, length = 255)
    public String getFileHash() {
        return fileHash;
    }

    public void setFileHash(String fileHash) {
        this.fileHash = fileHash;
    }

    @Basic
    @Column(name = "aliyun_hash", nullable = true, length = 255)
    public String getAliyunHash() {
        return aliyunHash;
    }

    public void setAliyunHash(String aliyunHash) {
        this.aliyunHash = aliyunHash;
    }

    @Basic
    @Column(name = "netease_hash", nullable = true, length = 255)
    public String getNeteaseHash() {
        return neteaseHash;
    }

    public void setNeteaseHash(String neteaseHash) {
        this.neteaseHash = neteaseHash;
    }

    @Basic
    @Column(name = "qcloud_hash", nullable = true, length = 255)
    public String getQcloudHash() {
        return qcloudHash;
    }

    public void setQcloudHash(String qcloudHash) {
        this.qcloudHash = qcloudHash;
    }

    @Basic
    @Column(name = "qiniu_hash", nullable = true, length = 255)
    public String getQiniuHash() {
        return qiniuHash;
    }

    public void setQiniuHash(String qiniuHash) {
        this.qiniuHash = qiniuHash;
    }

    @Basic
    @Column(name = "upyun_hash", nullable = true, length = 255)
    public String getUpyunHash() {
        return upyunHash;
    }

    public void setUpyunHash(String upyunHash) {
        this.upyunHash = upyunHash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesHashEntity that = (FilesHashEntity) o;

        if (id != that.id) return false;
        if (fileHash != null ? !fileHash.equals(that.fileHash) : that.fileHash != null) return false;
        if (aliyunHash != null ? !aliyunHash.equals(that.aliyunHash) : that.aliyunHash != null) return false;
        if (neteaseHash != null ? !neteaseHash.equals(that.neteaseHash) : that.neteaseHash != null) return false;
        if (qcloudHash != null ? !qcloudHash.equals(that.qcloudHash) : that.qcloudHash != null) return false;
        if (qiniuHash != null ? !qiniuHash.equals(that.qiniuHash) : that.qiniuHash != null) return false;
        if (upyunHash != null ? !upyunHash.equals(that.upyunHash) : that.upyunHash != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fileHash != null ? fileHash.hashCode() : 0);
        result = 31 * result + (aliyunHash != null ? aliyunHash.hashCode() : 0);
        result = 31 * result + (neteaseHash != null ? neteaseHash.hashCode() : 0);
        result = 31 * result + (qcloudHash != null ? qcloudHash.hashCode() : 0);
        result = 31 * result + (qiniuHash != null ? qiniuHash.hashCode() : 0);
        result = 31 * result + (upyunHash != null ? upyunHash.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    public FilesEntity getFilesIdByFileId() {
        return filesIdByFileId;
    }

    public void setFilesIdByFileId(FilesEntity filesIdByFileId) {
        this.filesIdByFileId = filesIdByFileId;
    }
}
