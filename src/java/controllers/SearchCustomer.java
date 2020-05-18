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

/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/search"})
public class SearchCustomer extends HttpServlet {
    private CustomerDAO cDAO = new CustomerImpl();
    private ArrayList<Customer> listCustomers;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        listCustomers = cDAO.getCustomersByName(keyword);
        request.setAttribute("listCustomers", listCustomers);
        request.getRequestDispatcher("/result").forward(request, response);
    }
}