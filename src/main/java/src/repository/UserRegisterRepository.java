package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import src.model.UserRegistrationEntity;

/**
 * Created by vellerzheng on 2017/9/29.
 */
@Repository
public interface UserRegisterRepository extends JpaRepository<UserRegistrationEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update UserRegistrationEntity us set us.registration=:qRegistration,us.registrationType=:qRegistrationType where us.userByUserId=:qId")
    public void updateUser(@Param("qRegistration") String registration, @Param("qRegistrationType") String RegistrationType,
                           @Param("qId") Integer userId);
}
