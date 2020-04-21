/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.CustomerDAO;
import dao.LocationDAO;
import dao.NameDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Customer;
import models.Name;
import models.DBConnect;
import models.Location;

/**
 *
 * @author CongNguyen
 */
public class CustomerImpl implements CustomerDAO{
    @Override
    public Customer getCustomer(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select * from customers where id = ?";
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Customer customer = new Customer();
            while(rs.next()){
                customer.setId(rs.getInt("id"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone_number(rs.getString("phone_number"));
                customer.setStage(rs.getInt("stage"));
                NameDAO nameimp = new NameImpl();
                Name name = nameimp.getName(rs.getInt("name_id"));
                customer.setName(name);
                LocationDAO locationimp = new LocationImpl();
                ArrayList<Location> listlocatino = locationimp.getLocationCustomer(rs.getInt("id"));
                customer.setLocation(listlocatino);
            }
            return customer;
        } catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getAllCustomers() {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select * from customers";
        ArrayList<Customer> listcustomer = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone_number(rs.getString("phone_number"));
                customer.setStage(rs.getInt("stage"));
                NameDAO nameimp = new NameImpl();
                Name name = nameimp.getName(rs.getInt("name_id"));
                customer.setName(name);
                LocationDAO locationimp = new LocationImpl();
                ArrayList<Location> listlocatino = locationimp.getLocationCustomer(rs.getInt("id"));
                customer.setLocation(listlocatino);
                listcustomer.add(customer);
            }
            return listcustomer;
        } catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        return null;
    }

    @Override
    public ArrayList<Customer> getCustomersByName(String name) {
       Connection conn = DBConnect.getDBConnection();
        String sql = "select * from customers inner join names on customers.name_id = names.id "
                + "where names.first_name like ?"; 
        ArrayList<Customer> listcustomer = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, "%"+name+"%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone_number(rs.getString("phone_number"));
                customer.setStage(rs.getInt("stage"));
                Name n = new Name();
                n.setFirst_name(rs.getString("first_name"));
                n.setLast_name(rs.getString("last_name"));
                n.setId(rs.getInt("name_id"));
                customer.setName(n);
                LocationDAO locationimp = new LocationImpl();
                ArrayList<Location> listlocatino = locationimp.getLocationCustomer(rs.getInt("id"));
                customer.setLocation(listlocatino);
                listcustomer.add(customer);
            }
            return listcustomer;
        } catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        return null;
    }
}
