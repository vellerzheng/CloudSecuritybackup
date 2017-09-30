package src.model;

import javax.persistence.*;

/**
 * Created by vellerzheng on 2017/9/30.
 */
@Entity
@Table(name = "user_auths", schema = "cloud_secureStorage", catalog = "")
public class UserAuthsEntity {
    private int id;
    private Integer userId;
    private String identifier;
    private String identityType;
    private String credential;
    private UsersEntity AuthsByUserId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = true)
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "identifier", nullable = true, length = 30)
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    @Basic
    @Column(name = "identity_type", nullable = true, length = 30)
    public String getIdentityType() {
        return identityType;
    }

    public void setIdentityType(String identityType) {
        this.identityType = identityType;
    }

    @Basic
    @Column(name = "credential", nullable = true, length = 30)
    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserAuthsEntity that = (UserAuthsEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (identifier != null ? !identifier.equals(that.identifier) : that.identifier != null) return false;
        if (identityType != null ? !identityType.equals(that.identityType) : that.identityType != null) return false;
        if (credential != null ? !credential.equals(that.credential) : that.credential != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        result = 31 * result + (identityType != null ? identityType.hashCode() : 0);
        result = 31 * result + (credential != null ? credential.hashCode() : 0);
        return result;
    }

    @OneToOne(mappedBy = "userById")
    public UsersEntity getAuthsByUserId() {
        return AuthsByUserId;
    }

    public void setAuthsByUserId(UsersEntity authsByUserId) {
        AuthsByUserId = authsByUserId;
    }
}
