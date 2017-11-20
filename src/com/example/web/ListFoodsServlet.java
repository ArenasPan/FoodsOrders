package com.example.web;

import com.example.domain.Foods;
import com.example.factory.BasicFactory;
import com.example.service.FoodsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Pan on 16/12/3.
 */
@WebServlet(name = "ListFoodsServlet", urlPatterns = "/servlet/ListFoodsServlet")
public class ListFoodsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        FoodsService service = BasicFactory.getFactory().getInstance(FoodsService.class);
        try {
            List<Foods> foodses = service.getAllCust();
            request.setAttribute("list", foodses);
            request.getRequestDispatcher("/listFoods.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
