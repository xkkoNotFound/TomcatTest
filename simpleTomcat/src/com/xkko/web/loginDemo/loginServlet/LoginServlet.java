package com.xkko.web.loginDemo.loginServlet;

import com.xkko.web.loginDemo.DAO.UserTableDAO;
import com.xkko.web.loginDemo.DO.UserTableDO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    UserTableDAO utd = new UserTableDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String userName = request.getParameter("username");
        UserTableDO ut = new UserTableDO(userName, request.getParameter("password"));
        UserTableDO utRes = utd.query(ut);
        if (utRes == null) {
            request.getRequestDispatcher("/failServlet").forward(request, response);
        } else {
            request.setAttribute("userName", userName);
            request.getRequestDispatcher("/successServlet").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
