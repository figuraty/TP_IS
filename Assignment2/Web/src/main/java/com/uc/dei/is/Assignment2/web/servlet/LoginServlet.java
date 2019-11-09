
package com.uc.dei.is.Assignment2.web.servlet;

import com.uc.dei.is.Assignment2.business.controller.OperationsController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("itemID"));
        if (session.getAttribute("userEmail") != null) {
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }

        request.getRequestDispatcher("/index.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        int loginResult = OperationsController.login(email, password);

        if (loginResult != 0){
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", email);
            response.sendRedirect(request.getContextPath() + "/home");
            request.removeAttribute("errorMsg");
        } else {
            request.setAttribute("errorMsg", "true");
            request.getRequestDispatcher("/index.jsp")
                    .forward(request, response);
        }
    }
}
