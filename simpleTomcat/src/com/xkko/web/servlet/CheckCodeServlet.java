package com.xkko.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 创建图片
        int width = 100;
        int height = 50;
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //2. 美化图片
        //2.1 填充背景色
        Graphics graphics = bi.getGraphics();   //画笔对象
        graphics.setColor(Color.CYAN);
        graphics.fillRect(0, 0, width, height);
        //2.2 画边框
        graphics.setColor(Color.RED);
        graphics.drawRect(0, 0, width - 1, height - 1);
        //2.3 写验证码
        String code = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasldkfjhgzmxncbv1234567890";
        Random random = new Random();
        for (int i = 1; i <= 4; i++) {
            int index = random.nextInt(code.length());
            char ch = code.charAt(index);
            graphics.drawString(ch + "", width / 5 * i, height / 2);
        }
        //2.4 画线
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(width);
            int x2 = random.nextInt(width);
            int y1 = random.nextInt(height);
            int y2 = random.nextInt(height);
            graphics.drawLine(x1, x2, y1, y2);
        }
        //3. 页面显示
        ImageIO.write(bi, "jpg", response.getOutputStream());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
