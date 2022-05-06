package pers.hm.security.servlet;

import pers.hm.security.cache.CustomiseSessionContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * TokeValilidateServlet.java
 *
 * @description:
 * @author: Heng Ma
 * @email: hema4295@uni.sydney.edu.au
 * @since: 06/05/2022
 */
@WebServlet(urlPatterns = "/inValilidateToken")
public class TokeInValilidateServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();

        String token = req.getParameter("token");
        if (null == token || "".equals(token)) {
            out.println("<font color='red' size='6'>toke is null, fail to invalidate it</font>");
            out.close();
            return;
        }
        CustomiseSessionContext sessContext = CustomiseSessionContext.getInstance();
        HttpSession sess = sessContext.getSession(token);
        // invalidate the token

        if (null != sess) {
            sess.invalidate();
            out.println("<font color='red' size='6'>Invalidate the token successfully</font>");
            out.close();
        }else {
            out.println("<font color='red' size='6'>The Token is already Invalidated</font>");
            out.close();
        }

    }


}
