/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.LevelDAO;
import dao.implement.LevelImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Level;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/config"})
public class ConfigController extends HttpServlet {
    private HttpSession httpSession;
    private ArrayList<Level> listLevel;
    private LevelDAO lDAO = new LevelImpl();
            
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        httpSession = request.getSession();
        if (httpSession.getAttribute("user") == null) {
            request.getRequestDispatcher("/login").forward(request, response);
        } 
        listLevel = lDAO.getAll();
        request.setAttribute("listLevel", listLevel);
        request.getRequestDispatcher("/config.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        String id = request.getParameter("id");
        JSONObject le = new JSONObject();
        Level level = lDAO.getLevel(Integer.parseInt(id));
        try {
            le.put("name", level.getName());
            le.put("price", level.getPrice());
            le.put("start_index", level.getStart_index());
            le.put("end_index", level.getEnd_index());
            le.put("id", level.getId());
            response.getWriter().write(le.toString());
        } catch (JSONException ex) {
            Logger.getLogger(ConfigController.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        
    }
}
