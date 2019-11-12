
package com.uc.dei.is.Assignment2.web.servlet;

import com.uc.dei.is.Assignment2.business.controller.OperationsController;
import com.uc.dei.is.Assignment2.data.models.Item;
import com.uc.dei.is.Assignment2.data.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "itemDetailsServlet", urlPatterns = {"/itemDetails"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class ItemDetailsServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "resources";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        session.setAttribute("userName", OperationsController.getUserName(userEmail));
        User user = OperationsController.getUser(userEmail);
        session.setAttribute("user", user);
        String itemID = (String) session.getAttribute("itemID");
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
            String picture = UploadFile(request, response);
            String initialInsertionDate = request.getParameter("initialInsertionDate");
            String price = request.getParameter("price");

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date itemDate = formatter.parse(initialInsertionDate);
                OperationsController.updateItem(Integer.parseInt(itemID), name, category, country, picture, itemDate, Float.parseFloat(price));
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

    private String UploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String fileName = "";
        for(Part parte : request.getParts()){
            System.out.println("\n\n\n" + parte.getName());
        }
        Part part = request.getParts().stream().skip(1).findFirst().orElse(null);
        fileName = part.getSubmittedFileName();
        part.write(uploadFilePath + File.separator + fileName);

        return "resources/" + fileName;
    }
}
