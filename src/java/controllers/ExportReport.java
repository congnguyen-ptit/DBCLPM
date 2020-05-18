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
import javax.servlet.http.HttpSession;
import models.Customer;
import java.util.ArrayList;

/**
 *
 * @author CongNguyen
 */
@WebServlet({"/export-report"})
public class ExportReport extends HttpServlet {
    private HttpSession httpSession;
    private CustomerDAO cDAO = new CustomerImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String error = "";
       httpSession = request.getSession();
       if (httpSession.getAttribute("user") != null) {
            ArrayList<Customer> listCustomers = cDAO.getAllCustomers();
            request.setAttribute("listCustomers", listCustomers);
            request.getRequestDispatcher("/exportreport.jsp").forward(request, response);
        } 
        else {
            error = "Bạn phải đăng nhập trước!";
            request.setAttribute("error", error);
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }
}
