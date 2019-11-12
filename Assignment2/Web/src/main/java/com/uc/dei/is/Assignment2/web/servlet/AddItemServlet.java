package com.uc.dei.is.Assignment2.web.servlet;

import com.uc.dei.is.Assignment2.business.manager.DataTransactionManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@WebServlet(name = "AddItemServlet", urlPatterns = {"/additem"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class AddItemServlet extends HttpServlet {

    private static final long serialVersionUID = 205242440643911308L;
    private static final String UPLOAD_DIR = "resources";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        if (session.getAttribute("userEmail") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/jsp/additem.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String email = (String) session.getAttribute("userEmail");
        String name = request.getParameter("name");
        String country = request.getParameter("country");
        String category = request.getParameter("category");
        String price = request.getParameter("price");

        String imageDirectory = UploadFile(request, response);

        DataTransactionManager.addItem(email, name, category, country, imageDirectory, Float.parseFloat(price));
        response.sendRedirect(request.getContextPath() + "/home");
    }

    private String UploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;

        File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }

        String fileName = "";
        Part part = request.getParts().iterator().next();
        fileName = part.getSubmittedFileName();
        part.write(uploadFilePath + File.separator + fileName);

        return "resources/" + fileName;
    }
}
