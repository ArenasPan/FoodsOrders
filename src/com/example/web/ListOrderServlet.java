package com.example.web;

import com.example.domain.Order;
import com.example.factory.BasicFactory;
import com.example.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Administrator on 2017/2/15.
 */
@WebServlet(name = "ListOrderServlet", urlPatterns = "/servlet/ListOrderServlet")
public class ListOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
        try {
            List<Order> orderList = service.getAllOrders();
            request.setAttribute("list", orderList);
            request.getRequestDispatcher("/listOrder.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
