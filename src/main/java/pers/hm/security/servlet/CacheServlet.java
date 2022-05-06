package pers.hm.security.servlet;

import pers.hm.security.cache.SecurityCache;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.time.LocalDateTime;

/**
 * CacheServlet.java
 *
 * @description: once the service start, load user & role data to the cache
 * @author: Heng Ma
 * @since: 05/05/2022
 */
@WebServlet(urlPatterns = "/loadCache", loadOnStartup = 2)
public class CacheServlet extends HttpServlet{

    @Override
    public void init() throws ServletException {
        System.out.println("load the cache..." + LocalDateTime.now());
        SecurityCache.getInstance();
    }
}
