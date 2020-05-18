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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Account;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/login"})

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AccountDAO accountDAO = new AccountImpl();
    private HttpSession httpSession;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        httpSession = request.getSession();
        if (httpSession.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/admin/home");
        } else {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
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
            JSONObject url = new JSONObject();
            String redirect = request.getContextPath() + "/admin/home";
            try {
                url.put("url", redirect);
                response.getWriter().write(url.toString());
            } catch (JSONException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            error = "Sai tên đăng nhập hoặc mật khẩu!!!";
            JSONObject errOb = new JSONObject();
            try {
                errOb.put("error", error);
                response.getWriter().write(errOb.toString());
            } catch (JSONException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
