package com.mcloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.mcloud.model.UserAuthsEntity;

/**
 * Created by vellerzheng on 2017/9/29.
 */
@Repository
public interface UserRegisterRepository extends JpaRepository<UserAuthsEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update UserAuthsEntity auth set auth.usersByUserId.id=:qAuthId," +
            "auth.identifier =:qIdentifier,auth.identityType=:qIdentityType,auth.credential=:qCredential where auth.id=:qId")
    public void updateUser(@Param("qAuthId") String AuthID, @Param("qIdentifier")String Identifier,
                           @Param("qIdentityType")String IdentityType,
                           @Param("qCredential")String Credential, @Param("qId") Integer Id);
}
