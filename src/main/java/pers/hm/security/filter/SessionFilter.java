package pers.hm.security.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * SessionFilter.java
 *
 * @description:
 * @author: Heng Ma
 * @email: hema4295@uni.sydney.edu.au
 * @since: 06/05/2022
 */
@WebFilter(filterName = "SessionFilter", urlPatterns = { "/user/*","/role/*" })
public class SessionFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String path = req.getContextPath();
        response.setContentType("test/html;charset=utf-8");

        // if not logging the system --> intercept it
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("current_user");
        if (obj == null) {
            System.out.println("Sorry！you haven't login the system，please try to login it！");
            resp.sendRedirect("/index.jsp");
            return;
        }
        // login --> call the next filter
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("destory...");
    }
}
