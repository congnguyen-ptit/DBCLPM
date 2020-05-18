/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.CustomerDAO;
import dao.LevelDAO;
import dao.PowerMeterDAO;
import dao.TimDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Customer;
import models.DBConnect;
import models.Level;
import models.PoweMeter;
import models.Tim;

/**
 *
 * @author CongNguyen
 */
public class PowerMeterImpl implements PowerMeterDAO{

    @Override
    public PoweMeter getMeter(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select power_meters.*, levels.id as leid\n" +
                        "from power_meters join power_level on power_meters.id = power_level.power_meter_id\n" +
                        "join levels on levels.id = power_level.level_id where power_meters.id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs =ps.executeQuery();
            PoweMeter pm = new PoweMeter();     
            while(rs.next()){
                pm.setId(rs.getInt("id"));
                pm.setNew_index(rs.getInt("new_index"));
                pm.setOld_index(rs.getInt("old_index"));
                TimDAO tDAO = new TimImpl();
                Tim t = tDAO.getTime(rs.getInt("time_id"));
                pm.setTime(t);
                CustomerDAO cDAO = new CustomerImpl();
                Customer c = cDAO.getCustomer(rs.getInt("customer_id"));
                pm.setCustomer(c);  
                LevelDAO lDAO = new LevelImpl();
                ArrayList<Level> listlevel = lDAO.getLevels(rs.getInt("leid"));
                pm.setLevels(listlevel);
            }
            return pm;
        }catch(SQLException e){
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;      
    }

    @Override
    public ArrayList<PoweMeter> getMeters(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select power_meters.*, levels.id as leid\n" +
                        "from power_meters join power_level on power_meters.id = power_level.power_meter_id\n" +
                        "join levels on levels.id = power_level.level_id where power_meters.customer_id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs =ps.executeQuery();
            ArrayList<PoweMeter> listPM = new ArrayList<>();
            while(rs.next()){
                PoweMeter pm = new PoweMeter();
                pm.setId(rs.getInt("id"));
                pm.setNew_index(rs.getInt("new_index"));
                pm.setOld_index(rs.getInt("old_index"));
                TimDAO tDAO = new TimImpl();
                Tim t = tDAO.getTime(rs.getInt("time_id"));
                pm.setTime(t);
                LevelDAO lDAO = new LevelImpl();
                ArrayList<Level> listlevel = lDAO.getLevels(rs.getInt("leid"));
                pm.setLevels(listlevel);
                listPM.add(pm);
            }
            return listPM;
        }catch(SQLException e){
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;    
    }
    
}
