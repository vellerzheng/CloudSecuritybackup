package src.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by vellerzheng on 2017/9/29.
 */
@Entity
@Table(name = "user", schema = "cloud_secureStorage", catalog = "")
public class UserEntity {
    private int userId;
    private String username;
    private String pwd;
    private Collection<UserRegistrationEntity> UserRegistrationByUserId;

    @Id
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username", nullable = false, length = 30)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "pwd", nullable = true, length = 30)
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserEntity that = (UserEntity) o;

        if (userId != that.userId) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (pwd != null ? !pwd.equals(that.pwd) : that.pwd != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (pwd != null ? pwd.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<UserRegistrationEntity> getUserRegistrationByUserId() {
        return UserRegistrationByUserId;
    }

    public void setUserRegistrationByUserId(Collection<UserRegistrationEntity> userRegistrationByUserId) {
        UserRegistrationByUserId = userRegistrationByUserId;
    }
}
