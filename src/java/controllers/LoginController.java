/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.AccountDAO;
import dao.implement.AccountImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;

/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/login"})

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDAO accountDAO = new AccountImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("user") != null) {
            System.out.println(" dcu");
            response.sendRedirect(request.getContextPath() + "/admin/home");
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String error = "";
        boolean isLogged = false;
        try{
            isLogged = accountDAO.checkLogin(username, password);
        }catch(Exception e){
            e.printStackTrace();
        }
        if (isLogged) {
            HttpSession httpSession = request.getSession();
            Account account = accountDAO.getAccount(username);
            httpSession.setAttribute("user", account.getName().toString());
            response.sendRedirect(request.getContextPath() + "/admin/home");
        } else {
            error = "Sai tên đăng nhập hoặc mật khẩu!!!";
            request.setAttribute("msg", error );
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
