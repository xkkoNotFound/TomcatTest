package com.xkko.web.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/downloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求参数
        String filename = request.getParameter("filename");
        //2.使用字节流加载进内存
        //2.1 获取文件真实路径
        ServletContext servletContext = request.getServletContext();
        String realPath = servletContext.getRealPath("/image/" + filename);
        //2.2 使用字节流关联
        FileInputStream fis = new FileInputStream(realPath);
        //3. 设置响应头
        String mimeType = servletContext.getMimeType(filename);
        response.setHeader("content-type", mimeType);
        response.setHeader("content-disposition", "attachment;filename=" + realPath);
        //4. 写入输出流
        ServletOutputStream sos = response.getOutputStream();
        byte[] bytes = new byte[1024 * 8];
        int len = 0;
        while ((len = fis.read(bytes)) != -1) {
            sos.write(bytes, 0, len);
        }
        fis.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
