package com.xkko.web.loginDemo.loginServlet;

import com.xkko.web.loginDemo.DAO.UserTableDAO;
import com.xkko.web.loginDemo.DO.UserTableDO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private UserTableDAO utd = new UserTableDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String userName = request.getParameter("username");
        UserTableDO ut = utd.queryName(new UserTableDO(userName, ""));
        while (ut != null) {
            request.getRequestDispatcher("register.html").forward(request, response);
        }
        String[] passwords = request.getParameterValues("password");
        while (!passwords[0].equals(passwords[1])) {
            request.getRequestDispatcher("register.html").forward(request, response);
        }
        int count = utd.insert(new UserTableDO(userName, passwords[0]));
        request.getRequestDispatcher("login.html").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
