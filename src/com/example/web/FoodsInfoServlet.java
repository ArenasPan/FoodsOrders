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

/**
 * Created by Pan on 16/12/4.
 */
@WebServlet(name = "FoodsInfoServlet",urlPatterns = "/servlet/FoodsInfoServlet")
public class FoodsInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        FoodsService service = BasicFactory.getFactory().getInstance(FoodsService.class);
        try {
            String id = request.getParameter("id");
            Foods foods = service.findCustById(id);
            request.setAttribute("foods", foods);
            request.getRequestDispatcher("/updateFoods.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
