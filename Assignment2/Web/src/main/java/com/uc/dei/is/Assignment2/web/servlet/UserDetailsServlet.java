
package com.uc.dei.is.Assignment2.web.servlet;

import com.uc.dei.is.Assignment2.business.controller.OperationsController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
        session.setAttribute("userName", OperationsController.getUserName(userEmail));
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
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        } else if (request.getParameter("delete") != null){
            String userEmail = (String) session.getAttribute("userEmail");
            OperationsController.deleteUser(userEmail);
            session.removeAttribute("userEmail");
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        } else if (request.getParameter("save") != null){
            String userEmail = (String) session.getAttribute("userEmail");
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String country = request.getParameter("country");
            int update = OperationsController.updateUser(userEmail, name, email, password, country);
            if (update != 0){
                session.setAttribute("userEmail", userEmail);
                request.removeAttribute("errorMsg");
                response.sendRedirect(request.getContextPath() + "/home");
                return;
            } else {
                request.setAttribute("errorMsg", "true");
                request.getRequestDispatcher("/WEB-INF/jsp/userDetails.jsp")
                        .forward(request, response);
                return;
            }
        } else if (request.getParameter("logout") != null){
            session.removeAttribute("userEmail");
            session.removeAttribute("errorMsg");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
