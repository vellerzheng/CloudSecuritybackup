package com.mcloud.repository;

import com.mcloud.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vellerzheng on 2017/10/25.
 */
@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> {
    @Modifying
    @Transactional
    @Query("update RoleEntity us set  us.roleName=:qRoleName," +
            "us.permissions=:qPermissions where us.roleId=:qRoleId")
    void updateRole(@Param("qRoleName") String roleName, @Param("qPermissions")String permission, @Param("qRoleId") Integer roleId);

    @Query("select usr from RoleEntity usr where usr.roleId =:qRid")
    RoleEntity  findRoleEntityById(@Param("qRid") int rid);
}




