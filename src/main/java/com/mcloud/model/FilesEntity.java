package com.mcloud.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

/**
 * Created by vellerzheng on 2017/10/13.
 */
@Entity
@Table(name = "files", schema = "cloud_secureStorage", catalog = "")
public class FilesEntity {
    private int id;
    private String description;
    private String fileName;
    private Integer status;
    private Integer version;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createtime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatetime;
    private String size;
    private UsersEntity userByUserId;
    private Collection<FilesHashEntity> HashFileIdById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "file_name", nullable = true, length = 255)
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public void setUpdatetime(Date updatetime) { this.updatetime = updatetime; }

    @Basic
    @Column(name = "size", nullable = true, length = 30)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilesEntity that = (FilesEntity) o;

        if (id != that.id) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (fileName != null ? !fileName.equals(that.fileName) : that.fileName != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return  false;
        if (version !=null ? !version.equals(that.version): that.version != null) return false;
        if (createtime != null ? !createtime.equals(that.createtime) : that.createtime != null) return false;
        if (updatetime != null ? !updatetime.equals(that.updatetime) : that.updatetime != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (version != null ? version.hashCode() : 0);
        result = 31 * result + (createtime != null ? createtime.hashCode() : 0);
        result = 31 * result + (updatetime != null ? updatetime.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public UsersEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UsersEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "filesIdByFileId")
    public Collection<FilesHashEntity> getHashFileIdById() {
        return HashFileIdById;
    }

    public void setHashFileIdById(Collection<FilesHashEntity> hashFileIdById) {
        HashFileIdById = hashFileIdById;
    }
}
