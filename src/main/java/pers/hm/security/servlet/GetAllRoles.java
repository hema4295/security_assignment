package pers.hm.security.servlet;

import pers.hm.security.cache.CustomiseSessionContext;
import pers.hm.security.domain.Role;
import pers.hm.security.domain.User;
import pers.hm.security.service.SecurityService;
import pers.hm.security.service.impl.SecurityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * GetAllRoles.java
 *
 * @description:
 * @author: Heng Ma
 * @email: hema4295@uni.sydney.edu.au
 * @since: 06/05/2022
 */
@WebServlet(urlPatterns = "/getAllRoles")
public class GetAllRoles extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        SecurityService securityService = new SecurityServiceImpl();

        String token = req.getParameter("token");
        if (null == token || "".equals(token)) {
            out.println("<font color='red' size='6'>token is null, fail to check it</font>");
            out.close();
            return;
        }

        CustomiseSessionContext sessContext = CustomiseSessionContext.getInstance();
        HttpSession sess = sessContext.getSession(token);
        if (null == sess) {
            out.println("<font color='red' size='6'>The Token is already Invalidated</font>");
            out.close();
            return;
        }
        // get the user
        User currentUser = (User) sess.getAttribute("current_user");
        Set<Role> roles = securityService.listRoles(currentUser.getUserName());

        if (null != roles && roles.size() > 0) {
            out.println("<font color='red' size='6'>The current user:" + currentUser.getUserName() + " belongs to the following roles: </font>");
            out.println("<table>");
            for (Role r : roles) {
                out.println("<tr><td>" + r.getRoleName() + "</td></tr>");
            }
            out.println("</table>");
            out.close();
        } else {
            out.println("<font color='red' size='6'>The current user:" + currentUser.getUserName() + " does NOT belong to any roles</font>");
            out.close();
        }
    }
}