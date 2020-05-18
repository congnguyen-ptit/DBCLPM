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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;

/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/customers/not-pay"})
public class CustomerNotPay extends HttpServlet {
private CustomerDAO cDAO = new CustomerImpl();
    private HttpSession httpSession;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        httpSession = request.getSession();
        String error = "";
        String title = "Chưa đóng tiền";
        if (httpSession.getAttribute("user") != null) {
            ArrayList<Customer> listCustomers = cDAO.getCustomersNotPay();
            for (int i=0;i<listCustomers.size();i++){
                System.out.println(listCustomers.get(i).getEmail());
            }
            request.setAttribute("listCustomers", listCustomers);
            request.setAttribute("tit", title);
            request.getRequestDispatcher("/list.jsp").forward(request, response);
        } 
        else {
            error = "Bạn phải đăng nhập trước!";
            request.setAttribute("error", error);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }
}
