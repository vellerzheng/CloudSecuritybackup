package src.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by vellerzheng on 2017/9/30.
 */
@Entity
@Table(name = "users", schema = "cloud_secureStorage", catalog = "")
public class UsersEntity {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private UserAuthsEntity userById;
    private Collection<FilesEntity> filesById;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "password", nullable = true, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 30)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (id != that.id) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id", nullable = false)
    public UserAuthsEntity getUserById() {
        return userById;
    }

    public void setUserById(UserAuthsEntity userById) {
        this.userById = userById;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<FilesEntity> getFilesById() {
        return filesById;
    }

    public void setFilesById(Collection<FilesEntity> filesById) {
        this.filesById = filesById;
    }
}
