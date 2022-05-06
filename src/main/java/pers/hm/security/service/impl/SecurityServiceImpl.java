package pers.hm.security.service.impl;

import pers.hm.security.cache.SecurityCache;
import pers.hm.security.domain.Role;
import pers.hm.security.domain.User;
import pers.hm.security.dto.ResponseDto;
import pers.hm.security.enums.ResponseCode;
import pers.hm.security.exception.SecurityBizException;
import pers.hm.security.service.SecurityService;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * SecurityServiceImpl.java
 *
 * @description: the service of User & Role, like CURD
 * @author: Heng Ma
 * @since: 05/05/2022
 */
public class SecurityServiceImpl implements SecurityService{

    @Override
    public ResponseDto createUser(String userName, String password) {
        ResponseDto responseDto = new ResponseDto();

        // check whether the use exists
        User u = SecurityCache.findUserByName(userName);
        if (null != u) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("Fail to create the user, the user name has already existed!");
            return responseDto;
        }

        // encrypt the password
        String encryptedPwd = null;
        try {
            encryptedPwd = generateMD5(password);
        } catch (Exception e) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg(e.getMessage());
            return responseDto;
        }

        // save the user
        if (null == encryptedPwd || "".equals(encryptedPwd)) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("fail to encrypted the password");
            return responseDto;
        }

        User user = new User();
        user.setUserName(userName);
        user.setPassword(encryptedPwd);
        user.setIsDelete(0);
        boolean addFlag = SecurityCache.getInstance().addUser(user);

        if (!addFlag) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("Fail to create the user!");
            return responseDto;
        }

        responseDto.setResultCode(ResponseCode.SUCCESS.code());
        return responseDto;
    }

    @Override
    public ResponseDto deleteUser(User uu) {
        ResponseDto responseDto = new ResponseDto();

        String userName = uu.getUserName();
        // check whether the user exists
        User user = SecurityCache.getInstance().findUserByName(userName);

        if (null == user) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("Fail to delete the user! the user doesn't exist!");
            return responseDto;
        }

        SecurityCache.getInstance().deleteUser(userName);
        responseDto.setResultCode(ResponseCode.SUCCESS.code());
        return responseDto;
    }

    @Override
    public ResponseDto createRole(String roleName) {
        ResponseDto responseDto = new ResponseDto();

        // check whether the role exists
        Role r = SecurityCache.findRoleByName(roleName);
        if (null != r) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("Fail to create the role, the role name has already existed!");
            return responseDto;
        }

        // save the role
        Role role = new Role();
        role.setRoleName(roleName);
        role.setIsDelete(0);
        boolean addFlag = SecurityCache.getInstance().addRole(role);

        if (!addFlag) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("Fail to create the role!");
            return responseDto;
        }

        responseDto.setResultCode(ResponseCode.SUCCESS.code());
        return responseDto;
    }

    @Override
    public ResponseDto deleteRole(Role rr) {
        ResponseDto responseDto = new ResponseDto();

        String roleName = rr.getRoleName();
        // check whether the role exists
        Role role = SecurityCache.getInstance().findRoleByName(roleName);

        if (null == role) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("Fail to delete the role! the role doesn't exist!");
            return responseDto;
        }

        SecurityCache.getInstance().deleteRole(roleName);
        responseDto.setResultCode(ResponseCode.SUCCESS.code());
        return responseDto;
    }

    /**
     * add a role for a user
     *
     * @param role
     * @param user
     * @return
     */
    public ResponseDto addRoleForUser(Role role, User user) {
        ResponseDto responseDto = new ResponseDto();

        // check where there is a association between the user and the role
        User u = SecurityCache.getInstance().findUserByName(user.getUserName());
        if (null == u) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("Fail to add role for user, the user is not existed!");
            return responseDto;
        }

        Role r = SecurityCache.getInstance().findRoleByName(role.getRoleName());
        if (null == u) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("Fail to add role for user, the role is not existed!");
            return responseDto;
        }

        // use set to store the role --> it make sure that the same role cannot be appeared many times
        HashSet<Role> roles = u.getRoles();
        if(null == roles){
            roles = new HashSet<>();
            roles.add(r);
            u.setRoles(roles);
        }else {
            u.getRoles().add(r);
        }
        responseDto.setResultCode(ResponseCode.SUCCESS.code());
        return responseDto;
    }

    @Override
    public ResponseDto login(String username, String password) {
        ResponseDto responseDto = new ResponseDto();

        User u = SecurityCache.getInstance().findUserByName(username);
        if (null == u) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("User is not exist!");
            return responseDto;
        }
        if (null == u.getPassword() || !u.getPassword().equals(generateMD5(password))) {
            responseDto.setResultCode(ResponseCode.FAIL.code());
            responseDto.setResultMsg("Password is wrong!");
            return responseDto;
        }
        responseDto.setResultCode(ResponseCode.SUCCESS.code());
        responseDto.setResultData(u);
        return responseDto;
    }

    @Override
    public ResponseDto invalidate() {
        return null;
    }

    /**
     * check whether the role belongs to the role
     *
     * @param user
     * @param role
     * @return
     */
    public boolean checkRoleByUser(User user, Role role) {
        HashSet<Role> roles = user.getRoles();
        if (null == roles || roles.size() == 0) {
            return false;
        }

        for (Role r : roles) {
            if (r.getRoleName().equals(role.getRoleName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * list all roles of the given userName
     *
     * @param userName
     * @return
     */
    public Set<Role> listRoles(String userName) {
        User u = SecurityCache.getInstance().getUserMap().get(userName);
        if (null == u) {
            return null;
        }
        return u.getRoles();
    }

    /**
     * generate MD5
     *
     * @param data
     * @return
     */
    public String generateMD5(String data) throws SecurityBizException {
        try {
            java.security.MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(data.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(String.format("%02X", item));
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("generating MD5 occurs NoSuchAlgorithmException" + e.getMessage());
            throw new SecurityBizException(ResponseCode.ENCRYPT_ERROR.code(), "no available algorithm when generating MD5");
        } catch (UnsupportedEncodingException e) {
            System.out.println("generating MD5 occurs UnsupportedEncodingException" + e.getMessage());
            throw new SecurityBizException(ResponseCode.ENCRYPT_ERROR.code(), "the encoding is unsupported when generating MD5");
        } catch (Exception e) {
            System.out.println("generating MD5 occurs Exception" + e.getMessage());
            throw new SecurityBizException(ResponseCode.ENCRYPT_ERROR.code(), "fail to generate MD5");
        }
    }

    /**
     * get all users
     *
     * @return
     */
    public ResponseDto getUsers() {
        ResponseDto responseDto = new ResponseDto();

        ArrayList<User> users = SecurityCache.listUsers();

        responseDto.setResultCode(ResponseCode.SUCCESS.code());
        responseDto.setResultData(users);
        return responseDto;
    }

    /**
     * get all valid roles
     *
     * @return
     */
    public ResponseDto getRoles() {
        ResponseDto responseDto = new ResponseDto();

        ArrayList<Role> roles = SecurityCache.listRoles();

        responseDto.setResultCode(ResponseCode.SUCCESS.code());
        responseDto.setResultData(roles);
        return responseDto;
    }
}
