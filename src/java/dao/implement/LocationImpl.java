/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.LocationDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.DBConnect;
import models.Location;

/**
 *
 * @author CongNguyen
 */
public class LocationImpl implements LocationDAO{

    @Override
    public ArrayList<Location> getLocationAccount(int id) {
        Connection conn = DBConnect.getDBConnection();
        ArrayList<Location> listlocation = new ArrayList<>();
        String sql = "select * from locations where account_id=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Location location = new Location();
                location.setId(rs.getInt("id"));
                location.setAddress(rs.getString("address"));
                location.setWard(rs.getString("ward"));
                location.setStreet(rs.getString("street"));
                location.setDistrict(rs.getString("district"));
                location.setProvince(rs.getString("province"));
                listlocation.add(location);
            }
            return listlocation;
        }catch(SQLException e){
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }

    @Override
    public ArrayList<Location> getLocationCustomer(int id) {
        Connection conn = DBConnect.getDBConnection();
        ArrayList<Location> listlocation = new ArrayList<>();
        String sql = "select * from locations where customer_id=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Location location = new Location();
                location.setId(rs.getInt("id"));
                location.setAddress(rs.getString("address"));
                location.setWard(rs.getString("ward"));
                location.setStreet(rs.getString("street"));
                location.setDistrict(rs.getString("district"));
                location.setProvince(rs.getString("province"));
                listlocation.add(location);
            }
            return listlocation;
        }catch(SQLException e){
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }
    
    
}
