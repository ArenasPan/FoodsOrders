package com.example.web;

import com.example.factory.BasicFactory;
import com.example.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/2/15.
 */
@WebServlet(name = "UpdateOrderServlet", urlPatterns = "/servlet/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);

        String id = request.getParameter("id");
        //再删除数据库中记录
        service.updateServedByOrderId(id);
        request.getRequestDispatcher("/servlet/ListOrderServlet").forward(request, response);
    }
}
