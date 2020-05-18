/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.AccountDAO;
import dao.LocationDAO;
import dao.NameDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Account;
import models.DBConnect;
import models.Location;
import models.Name;
import models.Role;
import java.util.ArrayList;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author CongNguyen
 */
public class AccountImpl implements AccountDAO{

    @Override
    public Account getAccount(String username) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select a.*, r.name AS rolename FROM accounts as a INNER JOIN roles AS r ON a.role_id = r.id WHERE a.username = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            Account account = new Account();
            while(rs.next()) {
                account.setId(rs.getInt("id"));
                account.setEmail(rs.getString("email"));
                account.setPhone_number(rs.getString("phone_number"));
                account.setUsername(rs.getString("username"));
                account.setPassword(rs.getString("password"));
                NameDAO nameimp = new NameImpl();
                Name name = nameimp.getName(rs.getInt("name_id"));
                account.setName(name);
                LocationDAO locationimp = new LocationImpl();
                ArrayList<Location> listlocation = locationimp.getLocationAccount(rs.getInt("id"));
                account.setLocation(listlocation);
                Role role = new Role();
                role.setName(rs.getString("rolename"));
                role.setId(rs.getInt("role_id"));
                account.setRole(role);
            }
            return account;
        }catch(SQLException e){
            DBConnect.dbClose();
            e.printStackTrace();
        }
        DBConnect.dbClose();
        return null;
    }

    @Override
    public boolean checkLogin(String username, String password) {
        Connection conn = DBConnect.getDBConnection();
        boolean check = false;
        String sql = "select * from accounts where username=?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                check = BCrypt.checkpw(password, rs.getString("password"));
            }
            return check;
        }catch(SQLException e){
            e.printStackTrace();
            DBConnect.dbClose();
        }
        return false;
    }
}
