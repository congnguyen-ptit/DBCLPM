/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Mail.MailUtil;
import dao.CustomerDAO;
import dao.MessageDAO;
import dao.implement.CustomerImpl;
import dao.implement.MessageImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;
import models.Messag;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/customer/send"})
public class SendEmailController extends HttpServlet {
    private CustomerDAO cDAO = new CustomerImpl();
    private Customer c;
    private boolean check = false;
    private HttpSession httpSession;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        httpSession = request.getSession();
        String error = "";
        if (httpSession.getAttribute("user") != null) {
            String ident = request.getParameter("id");
            if (ident != null) {
                try{
                    int id  = Integer.parseInt(ident);
                    c = cDAO.getCustomer(id);
                }catch(Exception e){
                    request.getRequestDispatcher("/customers/list").forward(request, response);
                }
                if (c.getId() != 0) {
                    request.setAttribute("customer", c);
                    request.getRequestDispatcher("/send.jsp").forward(request, response);
                } else {
                    error = "Không tồn tại khách hàng!";
                    request.setAttribute("mes", error);
                    request.getRequestDispatcher("/customers/list").forward(request, response);
                }
            } else {
                request.getRequestDispatcher("/customers/list").forward(request, response);
            }
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
        response.setContentType("application/json;charset=UTF-8");
        String content = request.getParameter("content");
        String subject = request.getParameter("subject");
        String mess = "";
        check = add(c, content);
        String[] email = new String[1];
        email[0] = c.getEmail();
        
        if (check) {
            try {
                MailUtil.sendMail(email, subject, content);
            } catch (MessagingException ex) {
                Logger.getLogger(SendEmailController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String redirect = request.getContextPath() + "/customers/list";
            JSONObject url = new JSONObject();
            try {
                url.put("url", redirect);
                response.getWriter().write(url.toString());
            } catch (JSONException ex) {
                Logger.getLogger(SendEmailController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            mess = "Gửi mail thất bại. Thử lại sau!";
            JSONObject errOb = new JSONObject();
            try {
                errOb.put("error", mess);
                response.getWriter().write(errOb.toString());
            } catch (JSONException ex) {
                Logger.getLogger(SendEmailController.class.getName()).log(Level.SEVERE, null, ex);
            }        
        }
    }
    
    public boolean add(Customer customer,String content){
        MessageDAO mDAO = new MessageImpl();
        Messag m = new Messag();
        m.setCustomer(customer);
        m.setContent(content);
        mDAO.storeMessage(m);
        return true;
    }
    
    
}
