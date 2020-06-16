/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.CustomerDAO;
import dao.TimDAO;
import dao.implement.CustomerImpl;
import dao.implement.TimImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;
import models.Tim;

/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/customers/paid"})
public class CustomerPaid extends HttpServlet {
    private CustomerDAO cDAO = new CustomerImpl();
    private HttpSession httpSession;
    private TimDAO tDAO = new TimImpl();
    ArrayList<Tim> listTime = tDAO.getAllTimes();
    String title = "Đã đóng tiền";
    ArrayList<Customer> listCustomers;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        httpSession = request.getSession();     
        if (httpSession.getAttribute("user") != null) {
            listCustomers = cDAO.getCustomersState(1); 
            request.setAttribute("listCustomers", listCustomers);
            request.setAttribute("listTime", listTime);
            request.setAttribute("tit", title);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } 
        else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("time");
        if (id.equals("0")) {
            listCustomers = cDAO.getCustomersState(1); 
            request.setAttribute("listCustomers", listCustomers);
            request.setAttribute("listTime", listTime);
            request.setAttribute("tit", title);
            request.getRequestDispatcher("/list.jsp").forward(request, response);   
        }
        else {
            listCustomers = cDAO.getCustomerByState(Integer.parseInt(id), 1); 
            request.setAttribute("listCustomers", listCustomers);
            request.setAttribute("listTime", listTime);
            request.setAttribute("tit", title);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        }
    }
    
}
