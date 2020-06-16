/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.CustomerDAO;
import dao.TimDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Customer;
import models.DBConnect;
import models.PoweMeter;
import models.Tim;

/**
 *
 * @author CongNguyen
 */
public class TimImpl implements TimDAO{

    @Override
    public Tim getTime(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select * from time where id=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Tim time = new Tim();
            while(rs.next()) {
                time.setId(rs.getInt("id"));
                time.setName(rs.getString("name"));
                time.setStart_date(rs.getDate("start_date"));
                time.setEnd_date(rs.getDate("end_date"));
            }
            return time;
        }catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }

    @Override
    public ArrayList<Tim> getAll(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "SELECT time.*, power_meters.id as pID, power_meters.customer_id as pCId,"
                + " power_meters.new_index as pNew, power_meters.old_index as pOld FROM time join "
                + "power_meters on time.id = power_meters.time_id where time.id=?; ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Tim> listTime = new ArrayList<>();
            while(rs.next()) {
                Tim time = new Tim();
                time.setName(rs.getString("name"));
                time.setId(rs.getInt("id"));
                time.setStart_date(rs.getDate("start_date"));
                time.setEnd_date(rs.getDate("end_date"));
                ArrayList<PoweMeter> listmetser = new ArrayList<>();
                PoweMeter pm = new PoweMeter();
                CustomerDAO cDAO = new CustomerImpl();
                Customer c = cDAO.getCustomer(rs.getInt("pCId"));
                pm.setId(rs.getInt("pID"));
                pm.setCustomer(c);
                pm.setNew_index(rs.getInt("pNew"));
                pm.setOld_index(rs.getInt("pOld"));
                TimDAO tDAO = new TimImpl();
                Tim t = tDAO.getTime(rs.getInt("id"));
                pm.setTime(t);
                time.setPowerMeter(listmetser);
                listTime.add(time);
            }
            return listTime;
        } catch(SQLException e ){
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }

    @Override
    public ArrayList<Tim> getAllTimes() {
        Connection conn = DBConnect.getDBConnection();
        ArrayList<Tim> listTime = new ArrayList<>();
        String sql = "select * from time;";
        try{
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Tim t = new Tim();
                t.setId(rs.getInt("id"));
                t.setName(rs.getString("name"));
                t.setStart_date(rs.getDate("start_date"));
                t.setEnd_date(rs.getDate("end_date"));
                listTime.add(t);
            }   
            return listTime;
        }catch(SQLException e){
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
        
    }
    
    
}
