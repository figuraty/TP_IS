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
//        String userEmail = (String) session.getAttribute("userEmail");
//        if (userEmail == null) {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return;
//        }
//        session.setAttribute("userName", OperationsController.getUserName(userEmail));
        session.setAttribute("userName", OperationsController.getUserName("abilio@gmail.com"));
        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String category = request.getParameter("category");
        System.out.println(category);
        if (category != null) {
            List<Item> items = OperationsController.getItemsByCategory(category);
            System.out.println(items.size());
            request.setAttribute("items", items);
            request.getRequestDispatcher("/WEB-INF/jsp/home.jsp")
                    .forward(request, response);
        } else
            response.sendRedirect(request.getContextPath() + "/login");

//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
//        String country = request.getParameter("country");
//
//        int register = OperationsController.register(name, email, password, country);
//
//        if (register != 0){
//            HttpSession session = request.getSession();
//            response.sendRedirect(request.getContextPath() + "/login");
//        } else {
//            request.setAttribute("errorMsg", "true");
//            request.getRequestDispatcher("/WEB-INF/jsp/register.jsp")
//                    .forward(request, response);
//        }

    }

//    @Override
//    protected void doPut(HttpServletRequest request,
//                                         HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        String category = (String) session.getAttribute("category");
//        if (category != null)
//            session.setAttribute("userName", category);
//        response.sendRedirect(request.getContextPath() + "/home");
//    }
}
