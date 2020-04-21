/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.NameDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.DBConnect;
import models.Name;

/**
 *
 * @author CongNguyen
 */
public class NameImpl implements NameDAO{

    @Override
    public Name getName(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select * from names where id=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Name name = new Name();
            while(rs.next()){
                name.setId(rs.getInt("id"));
                name.setFirst_name(rs.getString("first_name"));
                name.setLast_name(rs.getString("last_name"));
                
            }
            return name;
        }catch(SQLException e){
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }
    
}
