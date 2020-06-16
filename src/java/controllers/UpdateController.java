/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import dao.LevelDAO;
import dao.implement.LevelImpl;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Level;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author CongNguyen
 */
@WebServlet( urlPatterns = {"/config/update"})
public class UpdateController extends HttpServlet {
   private LevelDAO lDAO = new LevelImpl();
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String name = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int start_index = Integer.parseInt(request.getParameter("start_index"));
        int end_index = Integer.parseInt(request.getParameter("end_index"));
        int id = Integer.parseInt(request.getParameter("id"));
        Level level = lDAO.getLevel(id);
        level.setName(name);
        level.setPrice(price);
        level.setStart_index(start_index);
        level.setEnd_index(end_index);
        boolean updated = lDAO.updateLevel(level);
        System.out.println(updated);
        JSONObject res = new JSONObject();
        try {
           res.put("res", updated);
           response.getWriter().write(res.toString());
        } catch (JSONException ex) {
           Logger.getLogger(UpdateController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
