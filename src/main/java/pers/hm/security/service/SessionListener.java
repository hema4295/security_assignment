package pers.hm.security.service;

import pers.hm.security.cache.CustomiseSessionContext;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * SessionListener.java
 *
 * @description:
 * @author: Heng Ma
 * @email: hema4295@uni.sydney.edu.au
 * @since: 06/05/2022
 */
@WebListener
public class SessionListener implements HttpSessionListener{

    private CustomiseSessionContext sessContext = CustomiseSessionContext.getInstance();

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        sessContext.addSession(session);
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession session = httpSessionEvent.getSession();
        sessContext.delSession(session);
    }
}
