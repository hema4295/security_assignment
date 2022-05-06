package pers.hm.security.servlet;

import org.json.JSONObject;
import pers.hm.security.domain.Role;
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
 * RoleServlet.java
 *
 * @description:
 * @author: Heng Ma
 * @email: hema4295@uni.sydney.edu.au
 * @since: 06/05/2022
 */
@WebServlet(urlPatterns = "/role/*")
public class RoleServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String uri = req.getRequestURI();
        String path = uri.substring(uri.lastIndexOf("/"));

        SecurityService securityService = new SecurityServiceImpl();
        ResponseDto responseDto = new ResponseDto();

        if (path.equals("/add")) { // add a role
            System.out.println("start to add a role...");
            String roleName = req.getParameter("roleName");
            if (null == roleName || "".equals(roleName)) {
                responseDto.setResultCode(ResponseCode.FAIL.code());
                responseDto.setResultMsg("Fail to create the role, the role name should be NOT blank!");
            }

            System.out.println("add a user, username = " + roleName);
            responseDto = securityService.createRole(roleName);
            System.out.println("Complete adding a role, roleName = " + roleName + ", the response code is " + responseDto.getResultCode());
        } else if (path.equals("/list")) { // list all valid users
            // redirect
            resp.sendRedirect("/role_list.jsp");
        } else if (path.equals("/listRoles")) {
            System.out.println("start to get all roles...");
            responseDto = securityService.getRoles();
            System.out.println("Complete to get all users, the response code is " + responseDto.getResultCode());
        } else if (path.equals("/delete")) { // delete a role
            String roleName = req.getParameter("rolename");
            if (null == roleName || "".equals(roleName)) {
                responseDto.setResultCode(ResponseCode.FAIL.code());
                responseDto.setResultMsg("Fail to delete the role, the role name should be NOT blank!");
            }
            System.out.println("start to delete a role..., roleName = " + roleName);
            Role rr = new Role(roleName);
            responseDto = securityService.deleteRole(rr);
            System.out.println("Complete deleting the role, the response code is " + responseDto.getResultCode());
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        JSONObject jsonObj = new JSONObject(responseDto);
        out.println(jsonObj.toString());
        out.close();

    }
}
