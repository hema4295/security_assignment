package pers.hm.security.cache;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * CustomiseSessionContext.java
 *
 * @description:
 * @author: Heng Ma
 * @email: hema4295@uni.sydney.edu.au
 * @since: 06/05/2022
 */
public class CustomiseSessionContext{
    private static CustomiseSessionContext instance;
    private HashMap<String, HttpSession> sessionMap;

    private CustomiseSessionContext() {
        sessionMap = new HashMap<String, HttpSession>();
    }

    public static CustomiseSessionContext getInstance() {
        if (instance == null) {
            instance = new CustomiseSessionContext();
        }
        return instance;
    }

    public synchronized void addSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public synchronized void delSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public synchronized HttpSession getSession(String sessionID) {
        if (sessionID == null) {
            return null;
        }
        return sessionMap.get(sessionID);
    }
}
