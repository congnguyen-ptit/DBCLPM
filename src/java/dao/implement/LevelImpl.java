/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.LevelDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.DBConnect;
import models.Level;

/**
 *
 * @author CongNguyen
 */
public class LevelImpl implements LevelDAO{

    @Override
    public Level getLevel(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select * from levels where id=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Level level = new Level();
            while(rs.next()) {
                level.setId(rs.getInt("id"));
                level.setName(rs.getString("name"));
                level.setPrice(rs.getFloat("price"));
                level.setStart_index(rs.getInt("start_index"));
                level.setEnd_index(rs.getInt("end_index"));
            }
            return level;
        }catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }

    @Override
    public ArrayList<Level> getLevels(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select * from levels where id=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Level> listLevel = new ArrayList<>();
            while(rs.next()) {
                Level level = new Level();
                level.setId(rs.getInt("id"));
                level.setName(rs.getString("name"));
                level.setPrice(rs.getFloat("price"));
                level.setStart_index(rs.getInt("start_index"));
                level.setEnd_index(rs.getInt("end_index"));
                listLevel.add(level);
            }
            return listLevel;
        }catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }
    
    
}
