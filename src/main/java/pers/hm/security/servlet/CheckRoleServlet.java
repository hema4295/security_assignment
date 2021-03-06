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

/**
 * CheckRoleServlet.java
 *
 * @description:
 * @author: Heng Ma
 * @email: hema4295@uni.sydney.edu.au
 * @since: 06/05/2022
 */
@WebServlet(urlPatterns = "/checkRole")
public class CheckRoleServlet extends HttpServlet{
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

        String roleName = req.getParameter("roleName");
        if (null == roleName || "".equals(roleName)) {
            out.println("<font color='red' size='6'>role is null, fail to check it</font>");
            out.close();
            return;
        }

        CustomiseSessionContext sessContext = CustomiseSessionContext.getInstance();
        HttpSession sess = sessContext.getSession(token);

        if (null != sess) {
            // get the user
            User currentUser = (User) sess.getAttribute("current_user");
            Role role = new Role(roleName);
            boolean result = securityService.checkRoleByUser(currentUser, role);
            if (result) {
                out.println("<font color='red' size='6'>The current user:" + currentUser.getUserName() + " belongs to the role:" + roleName + "</font>");
                out.close();
            } else {
                out.println("<font color='red' size='6'>The current user:" + currentUser.getUserName() + " does NOT belong to the role:" + roleName + "</font>");
                out.close();
            }
        } else {
            out.println("<font color='red' size='6'>The Token is already Invalidated</font>");
            out.close();
        }
    }
}
