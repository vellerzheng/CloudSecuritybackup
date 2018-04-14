package com.mcloud.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by vellerzheng on 2017/10/25.
 */
@Entity
@Table(name = "role", schema = "cloud_secureStorage", catalog = "")
public class RoleEntity implements Serializable{
    private int roleId;
    private String roleName;
    private String permissions;
    private Collection<UsersEntity> roleIdById;

    @Id
    @Column(name = "roleId", nullable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "roleName", nullable = true, length = 20)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "permissions", nullable = true, length = 255)
    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleEntity that = (RoleEntity) o;

        if (roleId != that.roleId) return false;
        if (roleName != null ? !roleName.equals(that.roleName) : that.roleName != null) return false;
        if (permissions != null ? !permissions.equals(that.permissions) : that.permissions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roleId;
        result = 31 * result + (roleName != null ? roleName.hashCode() : 0);
        result = 31 * result + (permissions != null ? permissions.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userRoleIdByRoleId")
    public Collection<UsersEntity> getRoleIdById() {
        return roleIdById;
    }

    public void setRoleIdById(Collection<UsersEntity> roleIdById) {
        this.roleIdById = roleIdById;
    }
}
