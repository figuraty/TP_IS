package com.uc.dei.is.Assignment2.web.servlet;

import com.uc.dei.is.Assignment2.business.controller.OperationsController;
import com.uc.dei.is.Assignment2.business.manager.Filter;
import com.uc.dei.is.Assignment2.data.models.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        String userEmail = (String) session.getAttribute("userEmail");
        String category = request.getParameter("category");
        String logout = request.getParameter("logout");
        String searchFilters = request.getParameter("searchFilters");
        Filter filter = (Filter) session.getAttribute("filter");

        if (category != null) {
            List<Item> items = null;
            if (category.equalsIgnoreCase("All")) {
                session.removeAttribute("category");
                if (filter != null) {
                    filter.setCategory(null);
                    items = OperationsController.getItemsByFilter(filter);
                } else
                    items = OperationsController.getAllItems();
            }
            else {
                session.setAttribute("category", category);
                if (filter == null)
                    filter = new Filter(null, category, null, false, null, null, 0, 0);
                 else
                    filter.setCategory(category);

                items = OperationsController.getItemsByFilter(filter);
                request.setAttribute("items", items);
                request.getRequestDispatcher("/WEB-INF/jsp/home.jsp")
                        .forward(request, response);
            }
        } else if (searchFilters != null) {
            try {

                String name = request.getParameter("name");
                String myItems = request.getParameter("myItems");
                String myCountry = request.getParameter("myCountry");
                String initialInsertionDate = request.getParameter("initialInsertionDate");
                String finalInsertionDate = request.getParameter("finalInsertionDate");
                String initialPriceRange = request.getParameter("initialPriceRange");
                String finalPriceRange = request.getParameter("finalPriceRange");

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

                if (name == null || name.equals(""))
                    name = null;

                if (myItems == null || myItems.equals(""))
                    myItems = "false";
                else
                    myItems = "true";

                if (myCountry == null || myCountry.equals(""))
                    myCountry = null;
                else
                    myCountry = OperationsController.getUser(userEmail).getCountry();


                if (initialPriceRange == null || initialPriceRange.equals(""))
                    initialPriceRange = "0";

                if (finalPriceRange == null || finalPriceRange.equals(""))
                    finalPriceRange = "0";

                if (initialInsertionDate == null || initialInsertionDate.equals("")) {
                    if (finalInsertionDate == null || finalInsertionDate.equals("")) {
                        filter = new Filter(name, category, myCountry, Boolean.parseBoolean(myItems),
                                null, null,
                                Float.parseFloat(initialPriceRange), Float.parseFloat(finalPriceRange));
                    }
                    else {
                        filter = new Filter(name, category, myCountry, Boolean.parseBoolean(myItems),
                                null, formatter.parse(finalInsertionDate),
                                Float.parseFloat(initialPriceRange), Float.parseFloat(finalPriceRange));
                    }
                } else  if (finalInsertionDate == null || finalInsertionDate.equals(""))
                    filter = new Filter(name, category, myCountry, Boolean.parseBoolean(myItems),
                            formatter.parse(initialInsertionDate), null,
                            Float.parseFloat(initialPriceRange), Float.parseFloat(finalPriceRange));
                else {
                    filter = new Filter(name, category, myCountry, Boolean.parseBoolean(myItems),
                            formatter.parse(initialInsertionDate), formatter.parse(finalInsertionDate),
                            Float.parseFloat(initialPriceRange), Float.parseFloat(finalPriceRange));
                }

                session.setAttribute("filter", filter);


    //            List<Item> items = null;
    //            if (category.equalsIgnoreCase("All"))
    //                items = OperationsController.getAllItems();
    //            else
    //                items = OperationsController.getItemsByCategory(category);
    //            request.setAttribute("items", items);
    //            request.getRequestDispatcher("/WEB-INF/jsp/home.jsp")
    //                    .forward(request, response);
    //        } else {
    //            session.removeAttribute("userEmail");
    //            response.sendRedirect(request.getContextPath() + "/login");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
                        request.getRequestDispatcher("/WEB-INF/jsp/home.jsp")
                        .forward(request, response);
    }
}
