package pers.hm.security.cache;

import pers.hm.security.domain.Role;
import pers.hm.security.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * SecurityCache.java
 *
 * @description: all data will be persist
 * @author: Heng Ma
 * @since: 05/05/2022
 */
public class SecurityCache{

    private static HashMap<String, User> userMap = new HashMap<>();
    private static HashMap<String, Role> roleMap = new HashMap<>();

    private static SecurityCache instance = new SecurityCache();

    public static SecurityCache getInstance() {
        return instance;
    }

    private SecurityCache() {
        loadSecurityCache();
    }

    private static void loadSecurityCache() {
        System.out.println("start to load the cache...");
        // Test data
        User user = new User();
        String username = "Kevin";
        user.setUserName(username);
        user.setUserId(1l);
        user.setIsDelete(1);
        userMap.put(username, user);

        User user1 = new User();
        String username1 = "Julia";
        user1.setUserName(username1);
        user1.setUserId(2l);
        user1.setIsDelete(0);
        user1.setPassword("E10ADC3949BA59ABBE56E057F20F883E");
        userMap.put(username1, user1);

        User user2 = new User();
        String username2 = "Ace";
        user2.setUserName(username2);
        user2.setUserId(3l);
        user2.setIsDelete(0);
        userMap.put(username2, user2);

        Role role = new Role("Manager");
        role.setIsDelete(0);
        role.setRoleId(1l);
        roleMap.put(role.getRoleName(), role);

        Role role1 = new Role("Director");
        role1.setIsDelete(0);
        role1.setRoleId(2l);
        roleMap.put(role1.getRoleName(), role1);

        Role role2 = new Role("Leader");
        role2.setIsDelete(0);
        role2.setRoleId(3l);
        roleMap.put(role2.getRoleName(), role2);

        System.out.println("complete loading the cache...");
    }

    /**
     * find the valid user by given name
     *
     * @param name
     * @return
     */
    public static User findUserByName(String name) {
        if (null == userMap || userMap.isEmpty()) {
            return null;
        }

        if (userMap.containsKey(name)) {
            User u = userMap.get(name);
            if (null != u && null != u.getIsDelete() && u.getIsDelete() == 0) {
                return u;
            }
        }
        return null;
    }

    /**
     * add a user
     *
     * @param user
     * @return
     */
    public static boolean addUser(User user) {
        if (null != user) {
            userMap.put(user.getUserName(), user);
            return true;
        }
        return false;
    }

    /**
     * delete a user by given name
     * in this project, username is unique
     *
     * @param userName
     * @return
     */
    public static boolean deleteUser(String userName) {
        userMap.get(userName).setIsDelete(1);
        return true;
    }

    /**
     * all valid users
     *
     * @return
     */
    public static ArrayList<User> listUsers() {
        Collection<User> valueCollection = userMap.values();
        ArrayList<User> userList = new ArrayList<>();
        if (null != valueCollection && valueCollection.size() > 0) {
            valueCollection.forEach(v -> {
                if (null != v.getIsDelete() && v.getIsDelete() == 0) {
                    userList.add(v);
                }
            });
        }
        return userList;
    }


    /**
     * find the valid role by given name
     *
     * @param name
     * @return
     */
    public static Role findRoleByName(String name) {
        if (null == roleMap || roleMap.isEmpty()) {
            return null;
        }

        if (roleMap.containsKey(name)) {
            Role r = roleMap.get(name);
            if (null != r && null != r.getIsDelete() && r.getIsDelete() == 0) {
                return r;
            }
        }
        return null;
    }

    /**
     * add a role
     * @param role
     * @return
     */
    public static boolean addRole(Role role) {
        if (null != role) {
            roleMap.put(role.getRoleName(), role);
            return true;
        }
        return false;
    }

    /**
     * all valid roles
     *
     * @return
     */
    public static ArrayList<Role> listRoles() {
        Collection<Role> valueCollection = roleMap.values();
        ArrayList<Role> roleList = new ArrayList<>();
        if (null != valueCollection && valueCollection.size() > 0) {
            valueCollection.forEach(v -> {
                if (null != v.getIsDelete() && v.getIsDelete() == 0) {
                    roleList.add(v);
                }
            });
        }
        return roleList;
    }

    /**
     * delete a role by given name
     * in this project, role is unique in Role entity
     *
     * @param roleName
     * @return
     */
    public static boolean deleteRole(String roleName) {
        roleMap.get(roleName).setIsDelete(1);
        return true;
    }

    public static HashMap<String, User> getUserMap() {
        return userMap;
    }

    public static void setUserMap(HashMap<String, User> userMap) {
        SecurityCache.userMap = userMap;
    }

    public static HashMap<String, Role> getRoleMap() {
        return roleMap;
    }

    public static void setRoleMap(HashMap<String, Role> roleMap) {
        SecurityCache.roleMap = roleMap;
    }

    public static void setInstance(SecurityCache instance) {
        SecurityCache.instance = instance;
    }
}
