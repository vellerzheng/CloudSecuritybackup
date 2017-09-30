package src.model;

import javax.persistence.*;

/**
 * Created by vellerzheng on 2017/9/29.
 */
@Entity
@Table(name = "user_registration", schema = "cloud_secureStorage", catalog = "")
public class UserRegistrationEntity {
    private String registration;
    private String registrationType;
    private UserEntity userByUserId;

    @Basic
    @Column(name = "registration", nullable = false, length = 30)
    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Id
    @Column(name = "registration_type", nullable = false, length = 30)
    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRegistrationEntity that = (UserRegistrationEntity) o;

        if (registration != null ? !registration.equals(that.registration) : that.registration != null) return false;
        if (registrationType != null ? !registrationType.equals(that.registrationType) : that.registrationType != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = registration != null ? registration.hashCode() : 0;
        result = 31 * result + (registrationType != null ? registrationType.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }
}
