package com.example.web;

import com.alibaba.fastjson.JSON;
import com.example.domain.AllFoodsResponseInfo;
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
 * Created by Administrator on 2017/2/13.
 */
@WebServlet(name = "AddOrderServlet", urlPatterns = "/servlet/AddOrderServlet")
public class AddOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        OrderService service = BasicFactory.getFactory().getInstance(OrderService.class);
        String param = request.getParameter("param");
        if (param != null) {
            List<Order> orderList = JSON.parseArray(param, Order.class);
            int orderId = service.findLastOrderGroup() + 1;
            for (Order order : orderList) {
                order.setOrder_group(orderId);
            }
            service.addOrder(orderList);
        }
        AllFoodsResponseInfo responseInfo = new AllFoodsResponseInfo();
        responseInfo.setMsg("订单提交成功");
        responseInfo.setStatus(1);
        String json = JSON.toJSONString(responseInfo);
        response.getWriter().write(json);
    }
}
