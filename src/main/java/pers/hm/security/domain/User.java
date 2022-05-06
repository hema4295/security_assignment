package pers.hm.security.domain;

import java.io.Serializable;
import java.util.HashSet;

/**
 * User.java
 *
 * @description:
 * @author: Heng Ma
 * @since: 05/05/2022
 */
public class User implements Serializable{

    private Long userId;
    private String userName;
    private String password;
    private Integer isDelete;
    private HashSet<Role> roles;

    public User() {
    }

    public User(String userName) {
        this.userName = userName;
    }

    public User(Long userId, String userName, String password, Integer isDelete, HashSet<Role> roles) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.isDelete = isDelete;
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public HashSet<Role> getRoles() {
        return roles;
    }

    public void setRoles(HashSet<Role> roles) {
        this.roles = roles;
    }
}
