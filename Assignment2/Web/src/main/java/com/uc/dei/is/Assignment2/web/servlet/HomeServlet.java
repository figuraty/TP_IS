package com.uc.dei.is.Assignment2.web.servlet;

import com.uc.dei.is.Assignment2.business.controller.OperationsController;
import com.uc.dei.is.Assignment2.data.models.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        session.setAttribute("userName", OperationsController.getUserName(userEmail));
        List<Item> items = OperationsController.getAllItems();
        session.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String category = request.getParameter("category");
        String logout = request.getParameter("logout");
        if(logout != null){
            session.removeAttribute("userEmail");
            response.sendRedirect(request.getContextPath() + "/login");
        } else {
            if (category != null) {
                List<Item> items = null;
                if (category.equalsIgnoreCase("All"))
                    items = OperationsController.getAllItems();
                else
                    items = OperationsController.getItemsByCategory(category);
                request.setAttribute("items", items);
                request.getRequestDispatcher("/WEB-INF/jsp/home.jsp")
                        .forward(request, response);
            } else
                response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
