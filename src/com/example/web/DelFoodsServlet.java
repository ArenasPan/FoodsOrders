package com.example.web;

import com.example.factory.BasicFactory;
import com.example.service.FoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Pan on 16/12/4.
 */
@WebServlet(name = "DelFoodsServlet",urlPatterns = "/servlet/DelFoodsServlet")
public class DelFoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        FoodsService service = BasicFactory.getFactory().getInstance(FoodsService.class);
        String id = request.getParameter("id");
        //先删除图片
        service.deleteImageById(id);
        //再删除数据库中记录
        service.delCustById(id);
        request.getRequestDispatcher("/servlet/ListFoodsServlet").forward(request, response);
    }
}
