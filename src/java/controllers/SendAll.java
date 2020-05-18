/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Mail.MailUtil;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/customers/send-all"})
public class SendAll extends HttpServlet {
    private CustomerDAO cDAO = new CustomerImpl();
    private ArrayList<Customer> listCustomer;
    private HttpSession httpSession;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listCustomer = cDAO.getAllCustomers();
        httpSession = request.getSession();
        String error = "";
        if (httpSession.getAttribute("user") != null) {
            request.getRequestDispatcher("/sendalll.jsp").forward(request, response);
        } 
        else {
            error = "Bạn phải đăng nhập trước!";
            request.setAttribute("error", error);
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subject = request.getParameter("subject");
        String content = request.getParameter("content");
        String[] email = new String[listCustomer.size()];
        String error = "";
        boolean check = false;
        for(int i = 0;i<listCustomer.size();i++){
            email[i] = listCustomer.get(i).getEmail();
        }
        try {
            MailUtil.sendMail(email, subject, content);
            System.out.println("oke");
            check = true;
            response.sendRedirect(request.getContextPath() + "/customers/list");
        } catch (MessagingException ex) {
            Logger.getLogger(SendAll.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!check){
            error = "Error";
            request.setAttribute("msg", error);
            request.getRequestDispatcher("/customers/send-all").forward(request, response);
        }
    }
}
