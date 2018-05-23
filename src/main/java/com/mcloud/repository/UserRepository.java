package com.mcloud.repository;

import com.mcloud.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by vellerzheng on 2017/9/29.
 */
@Repository
public interface UserRepository extends JpaRepository<UsersEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update UsersEntity us set us.username=:qUsername,us.password=:qPassword," +
            "us.email=:qemail,us.phone=:qphone where us.id=:qId")
    void updateUser(@Param("qUsername") String username, @Param("qPassword") String password,@Param("qemail") String email,
                           @Param("qphone") String phone,@Param("qId") Integer Id);

    @Query("select u from UsersEntity u where u.username =:qUsername")
    UsersEntity findByUsernameEndsWith(@Param("qUsername") String username);

    @Query("select usr from UsersEntity usr where usr.id =:qUid ")
    UsersEntity  findUsersEntityById(@Param("qUid") int uid);

    @Query("SELECT usr from UsersEntity usr where usr.username =:qUsername or usr.phone =:qPhone or usr.email =:qEmail")
    UsersEntity findByUsernameOrPhoneOrEmail(@Param("qUsername")String username, @Param("qPhone")String phone, @Param("qEmail")String qEmail);

    @Modifying
    @Transactional
    @Query("update  UsersEntity us set us.username =:qUserName, us.password=:qPassword, us.updatetime=:qUpdatetime where us.id =:qId")
    void updateByName(@Param("qUserName")String userName, @Param("qPassword") String password,
                             @Param("qUpdatetime")Date updatetime, @Param("qId") Integer id);
}
