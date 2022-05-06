package pers.hm.security.domain;

import java.io.Serializable;

/**
 * Role.java
 *
 * @description:
 * @author: Heng Ma
 * @since: 05/05/2022
 */
public class Role implements Serializable{

    private Long roleId;
    private String roleName;
    private Integer isDelete;

    public Role() {
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}
