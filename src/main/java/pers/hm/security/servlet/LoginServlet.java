package pers.hm.security.servlet;

import pers.hm.security.dto.ResponseDto;
import pers.hm.security.enums.ResponseCode;
import pers.hm.security.service.SecurityService;
import pers.hm.security.service.impl.SecurityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LoginServlet.java
 *
 * @description:
 * @author: Heng Ma
 * @email: hema4295@uni.sydney.edu.au
 * @since: 06/05/2022
 */
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("userPwd");

        SecurityService securityService = new SecurityServiceImpl();
        ResponseDto responseDto = new ResponseDto();

        responseDto = securityService.login(userName, password);

        if (responseDto.getResultCode() == ResponseCode.SUCCESS.code()) {
            HttpSession session = req.getSession();
            session.setAttribute("current_user", responseDto.getResultData());
            session.setAttribute("token", session.getId());
            // 2h = 2 * 60 * 60
            session.setMaxInactiveInterval(7200);
            resp.sendRedirect(resp.encodeRedirectURL("/main.jsp"));
        }
    }
}
