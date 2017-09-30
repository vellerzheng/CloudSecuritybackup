package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import src.model.UserEntity;

/**
 * Created by vellerzheng on 2017/9/29.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update UserEntity us set us.username=:qUsername,us.pwd=:qPwd where us.userId=:qId")
    public void updateUser(@Param("qUsername") String username, @Param("qPwd") String pwd,
                           @Param("qId") Integer userId);
}
