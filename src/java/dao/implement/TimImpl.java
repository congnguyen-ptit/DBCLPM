/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.TimDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.DBConnect;
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
    
}
