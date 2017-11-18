package com.mcloud.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_advice", schema = "cloud_secureStorage", catalog = "")
public class UserAdviceEntity {
    private int id;
    private String name;
    private String email;
    private String mainIdea;
    private String messageDetail;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 32)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 32)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "main_idea", nullable = true, length = 128)
    public String getMainIdea() {
        return mainIdea;
    }

    public void setMainIdea(String mainIdea) {
        this.mainIdea = mainIdea;
    }

    @Basic
    @Column(name = "message_detail", nullable = true, length = -1)
    public String getMessageDetail() {
        return messageDetail;
    }

    public void setMessageDetail(String messageDetail) {
        this.messageDetail = messageDetail;
    }

    @Basic
    @Column(name = "submit_time", nullable = true)
    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAdviceEntity that = (UserAdviceEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (mainIdea != null ? !mainIdea.equals(that.mainIdea) : that.mainIdea != null) return false;
        if (messageDetail != null ? !messageDetail.equals(that.messageDetail) : that.messageDetail != null)
            return false;
        if (submitTime != null ? !submitTime.equals(that.submitTime) : that.submitTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (mainIdea != null ? mainIdea.hashCode() : 0);
        result = 31 * result + (messageDetail != null ? messageDetail.hashCode() : 0);
        result = 31 * result + (submitTime != null ? submitTime.hashCode() : 0);
        return result;
    }
}
