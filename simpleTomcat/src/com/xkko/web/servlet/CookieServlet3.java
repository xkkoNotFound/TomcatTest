package com.xkko.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/cookieServlet3")
public class CookieServlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Cookie[] cookies = request.getCookies();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        boolean flag = false;
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if ("lastTime".equals(cookie.getName())) {
                    String lastTime = cookie.getValue();
                    lastTime = URLDecoder.decode(lastTime, "utf-8");
                    response.getWriter().write("您上次访问的时间是 " + lastTime);
                    Date date = new Date();
                    String currentDate = dateFormat.format(date);
                    currentDate = URLEncoder.encode(currentDate, "utf-8");
                    cookie.setValue(currentDate);
                    cookie.setMaxAge(60);
                    response.addCookie(cookie);
                    flag = true;
                    break;
                }
            }
        }
        if (cookies == null || cookies.length == 0 || flag == false) {
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            currentDate = URLEncoder.encode(currentDate, "utf-8");
            Cookie cookie = new Cookie("lastTime", currentDate);
            cookie.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(cookie);
            response.getWriter().write("欢迎你");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
