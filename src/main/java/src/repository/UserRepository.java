package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import src.model.UsersEntity;

/**
 * Created by vellerzheng on 2017/9/29.
 */
@Repository
public interface UserRepository extends JpaRepository<UsersEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update UsersEntity us set us.username=:qUsername,us.password=:qPassword," +
            "us.email=:qemail,us.phone=:qphone where us.id=:qId")
    public void updateUser(@Param("qUsername") String username, @Param("qPassword") String password,@Param("qemail") String email,
                           @Param("qphone") String phone,@Param("qId") Integer Id);
}
