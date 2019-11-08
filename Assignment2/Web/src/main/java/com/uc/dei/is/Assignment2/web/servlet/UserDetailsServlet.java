
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


@WebServlet(name = "userDetailsServlet", urlPatterns = {"/userDetails"})
public class UserDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        session.setAttribute("user", OperationsController.getUser(userEmail));
        request.getRequestDispatcher("/WEB-INF/jsp/userDetails.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (request.getParameter("cancel") != null){
            System.out.println("CANCEL");
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        } else if (request.getParameter("delete") != null){
            System.out.println("DELETE");
            String userEmail = (String) session.getAttribute("userEmail");
            OperationsController.deleteUser(userEmail);
            session.removeAttribute("userEmail");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } else {
            System.out.println("UPDATE");
            String userEmail = (String) session.getAttribute("userEmail");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String country = request.getParameter("country");
            OperationsController.updateUser(userEmail, name, email, password, country);
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        }
    }
}
