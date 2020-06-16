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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import dao.PowerMeterDAO;
import dao.implement.PowerMeterImpl;
import models.PoweMeter;

/**
 *
 * @author CongNguyen
 */
@WebServlet({"/export-report"})
public class ExportReport extends HttpServlet {
    private HttpSession httpSession;
    private CustomerDAO cDAO = new CustomerImpl();
    private PowerMeterDAO pDAO = new PowerMeterImpl();
    ArrayList<Customer> listCustomers;
    ArrayList<PoweMeter> listMeters;
    double total;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("charset=utf8");
        String error = "";
        httpSession = request.getSession();
        if (httpSession.getAttribute("user") != null) {
            listMeters = pDAO.getAll();
            total = pDAO.getTotal(listMeters);
            request.setAttribute("listMeters", listMeters);
            request.setAttribute("type", 2);
            request.setAttribute("total", total);
            request.getRequestDispatcher("/exportreport.jsp").forward(request, response);
        } 
        else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        int status = Integer.parseInt(request.getParameter("status"));
        if (status == 2) {
            listMeters = pDAO.getFromToNoStatus(start_date, end_date);
            total = pDAO.getTotal(listMeters);
            request.setAttribute("listMeters", listMeters);
            request.setAttribute("type", status);
            request.setAttribute("total", total);
            request.getRequestDispatcher("/exportreport.jsp").forward(request, response);
        }
        if (status == 0) {
            listMeters = pDAO.getFromTo(start_date, end_date, 0);
            total = pDAO.getTotal(listMeters);
            request.setAttribute("listMeters", listMeters);
            request.setAttribute("type", status);
            request.setAttribute("start", start_date);
            request.setAttribute("end", end_date);
            request.setAttribute("total", total);
            request.getRequestDispatcher("/exportreport.jsp").forward(request, response);
        }
        if (status == 1) {
            listMeters = pDAO.getFromTo(start_date, end_date,1);
            total = pDAO.getTotal(listMeters);
            request.setAttribute("listMeters", listMeters);
            request.setAttribute("start", start_date);
            request.setAttribute("end", end_date);
            request.setAttribute("type", status);
            request.setAttribute("total", total);
            request.getRequestDispatcher("/exportreport.jsp").forward(request, response);
        }
    }
}
