
package com.uc.dei.is.Assignment2.web.servlet;

import com.uc.dei.is.Assignment2.business.controller.OperationsController;
import com.uc.dei.is.Assignment2.data.models.Item;
import com.uc.dei.is.Assignment2.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "itemDetailsServlet", urlPatterns = {"/itemDetails"})
public class ItemDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        String itemID = (String) session.getAttribute("itemID");
        if (userEmail == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        session.setAttribute("userName", OperationsController.getUserName(userEmail));
        User user = OperationsController.getUser(userEmail);
        session.setAttribute("user", user);
        Item item = OperationsController.getItem(Integer.parseInt(itemID));
        session.setAttribute("item", item);
        session.setAttribute("itemUserID", item.getUser().getId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String itemDate = dateFormat.format(item.getInsertionDate());
        session.setAttribute("itemDate", itemDate);
        request.getRequestDispatcher("/WEB-INF/jsp/itemDetails.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (request.getParameter("edit") != null){
            session.setAttribute("editItem", true);
            request.getRequestDispatcher("/WEB-INF/jsp/itemDetails.jsp")
                    .forward(request, response);
            return;
        } else if (request.getParameter("cancel") != null){
            session.setAttribute("editItem", false);
            request.getRequestDispatcher("/WEB-INF/jsp/itemDetails.jsp")
                    .forward(request, response);
            return;
        } else if (request.getParameter("delete") != null){
            String itemID = (String) session.getAttribute("itemID");
            OperationsController.deleteItem(Integer.parseInt(itemID));
            session.removeAttribute("item");
            session.removeAttribute("itemUserID");
            session.removeAttribute("editItem");
            response.sendRedirect(request.getContextPath() + "/home");
            return;
        } else if (request.getParameter("save") != null){
            String itemID = (String) session.getAttribute("itemID");
            String name = request.getParameter("name");
            String category = request.getParameter("category");
            String country = request.getParameter("country");
//            String picture = request.getParameter("picture");
            String initialInsertionDate = request.getParameter("initialInsertionDate");
            String price = request.getParameter("price");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date itemDate = formatter.parse(initialInsertionDate);
                OperationsController.updateItem(Integer.parseInt(itemID), name, category, country, "ref", itemDate, Float.parseFloat(price));
                session.removeAttribute("item");
                session.removeAttribute("itemUserID");
                session.removeAttribute("editItem");
                response.sendRedirect(request.getContextPath() + "/home");
            } catch (ParseException e) {
                e.printStackTrace();
                session.removeAttribute("item");
                session.removeAttribute("itemUserID");
                session.removeAttribute("editItem");
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else if (request.getParameter("logout") != null){
            session.removeAttribute("item");
            session.removeAttribute("itemUserID");
            session.removeAttribute("editItem");
            session.removeAttribute("userEmail");
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
}
