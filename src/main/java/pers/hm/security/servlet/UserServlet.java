package pers.hm.security.servlet;

import org.json.JSONObject;
import pers.hm.security.domain.User;
import pers.hm.security.dto.ResponseDto;
import pers.hm.security.enums.ResponseCode;
import pers.hm.security.service.SecurityService;
import pers.hm.security.service.impl.SecurityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * UserServlet.java
 *
 * @description:
 * @author: Heng Ma
 * @since: 05/05/2022
 */
@WebServlet(urlPatterns = "/user/*")
public class UserServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String uri = req.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));

        SecurityService securityService = new SecurityServiceImpl();
        ResponseDto responseDto = new ResponseDto();

        if (path.equals("/add")) { // add a user
            System.out.println("start to add a user...");
            String userName = req.getParameter("username");
            String password = req.getParameter("userPwd");
            if (null == userName || "".equals(userName)) {
                responseDto.setResultCode(ResponseCode.FAIL.code());
                responseDto.setResultMsg("Fail to create the user, the user name should be NOT blank!");
            }

            if (null == password || "".equals(password)) {
                responseDto.setResultCode(ResponseCode.FAIL.code());
                responseDto.setResultMsg("Fail to create the user, the password should be NOT blank!");
            }

            System.out.println("add a user, username = " + userName);
            responseDto = securityService.createUser(userName, password);
            System.out.println("Complete adding a user, username = " + userName + ", the response code is " + responseDto.getResultCode());
        } else if (path.equals("/list")) { // list all valid users
            // redirect
            resp.sendRedirect("/user_list.jsp");
        } else if (path.equals("/listUsers")) {
            System.out.println("start to get all users...");
            responseDto = securityService.getUsers();
            System.out.println("Complete to get all users, the response code is " + responseDto.getResultCode());
        } else if (path.equals("/delete")) { // delete a user
            String userName = req.getParameter("username");
            if (null == userName || "".equals(userName)) {
                responseDto.setResultCode(ResponseCode.FAIL.code());
                responseDto.setResultMsg("Fail to delete the user, the user name should be NOT blank!");
            }
            System.out.println("start to delete a user..., userName = " + userName);
            User uu = new User(userName);
            responseDto = securityService.deleteUser(uu);
            System.out.println("Complete deleting the user, the response code is " + responseDto.getResultCode());
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        JSONObject jsonObj = new JSONObject(responseDto);
        out.println(jsonObj.toString());
        out.close();

    }
}
