/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerDAO;
import dao.implement.CustomerImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Customer;
import java.util.ArrayList;
import javax.servlet.http.HttpSession;

/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/customers/list"})
public class ShowListSendEmailController extends HttpServlet {
    private CustomerDAO cDAO= new CustomerImpl();
    private HttpSession httpSession;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        httpSession = request.getSession();
        String error = "";
        if (httpSession.getAttribute("user") != null) {
            ArrayList<Customer> listCustomers = cDAO.getAllCustomers();
            request.setAttribute("listCustomers", listCustomers);
            request.getRequestDispatcher("/sendnotification.jsp").forward(request, response);
        } 
        else {
            error = "Bạn phải đăng nhập trước!";
            request.setAttribute("error", error);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
