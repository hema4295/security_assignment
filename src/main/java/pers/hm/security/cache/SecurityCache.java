package pers.hm.security.cache;

import pers.hm.security.domain.Role;
import pers.hm.security.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

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
        Role role = new Role(1l, "Manager", 0);
        roleMap.put(role.getRoleName(), role);

        Role role1 = new Role(2l, "Director", 0);
        roleMap.put(role1.getRoleName(), role1);

        Role role2 = new Role(3l, "Leader", 0);
        roleMap.put(role2.getRoleName(), role2);

        Role role3 = new Role(4l, "Salesmen", 0);
        roleMap.put(role3.getRoleName(), role3);

        Role role4 = new Role(5l, "Developer", 0);
        roleMap.put(role4.getRoleName(), role4);


        HashSet<Role> roles = new HashSet<Role>();
        roles.add(role);
        roles.add(role1);
        User user = new User(1l, "Kevin", "E10ADC3949BA59ABBE56E057F20F883E", 0, roles);
        userMap.put(user.getUserName(), user);

        HashSet<Role> roles1 = new HashSet<Role>();
        roles1.add(role1);
        roles1.add(role2);
        User user1 = new User(2l, "Julia", "E10ADC3949BA59ABBE56E057F20F883E", 0, roles1);
        userMap.put(user1.getUserName(), user1);


        HashSet<Role> roles2 = new HashSet<Role>();
        roles2.add(role1);
        roles2.add(role2);
        roles2.add(role3);
        User user2 = new User(3l, "Cathrine", "E10ADC3949BA59ABBE56E057F20F883E", 0, roles2);
        userMap.put(user2.getUserName(), user2);

        HashSet<Role> roles3 = new HashSet<Role>();
        roles3.add(role3);
        User user3 = new User(4l, "Ace", "E10ADC3949BA59ABBE56E057F20F883E", 0, roles3);
        userMap.put(user3.getUserName(), user3);

        HashSet<Role> roles4 = new HashSet<Role>();
        roles4.add(role1);
        roles4.add(role3);
        roles4.add(role4);
        User user4 = new User(5l, "Bella", "E10ADC3949BA59ABBE56E057F20F883E", 0, roles4);
        userMap.put(user4.getUserName(), user4);

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
     *
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
