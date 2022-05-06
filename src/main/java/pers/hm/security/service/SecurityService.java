package pers.hm.security.service;

import pers.hm.security.domain.Role;
import pers.hm.security.domain.User;
import pers.hm.security.dto.ResponseDto;

import java.util.Set;

/**
 * Created by maheng on 5/5/22.
 */
public interface SecurityService{

    /**
     * create a user
     *
     * @param userName
     * @param password
     * @return
     */
    public ResponseDto createUser(String userName, String password);

    public ResponseDto deleteUser(User uu);

    public ResponseDto createRole(String roleName);

    public ResponseDto deleteRole(Role rr);

    /**
     * add a role for a user
     *
     * @param role
     * @param user
     * @return
     */
    public ResponseDto addRoleForUser(Role role, User user);

    public ResponseDto login(String username, String password);

    public ResponseDto invalidate();

    /**
     * check whether the role belongs to the role
     *
     * @param user
     * @param role
     * @return
     */
    public boolean checkRoleByUser(User user, Role role);

    /**
     * list all roles of the given userName
     * @param userName
     * @return
     */
    public Set<Role> listRoles(String userName);

    /**
     * get all valid users
     *
     * @return
     */
    public ResponseDto getUsers();

    /**
     * get all valid roles
     *
     * @return
     */
    public ResponseDto getRoles();
}
