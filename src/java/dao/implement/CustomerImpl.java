/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.CustomerDAO;
import dao.InvoiceDAO;
import dao.LocationDAO;
import dao.NameDAO;
import dao.PowerMeterDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Customer;
import models.Name;
import models.DBConnect;
import models.Invoice;
import models.Location;
import models.PoweMeter;

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
                customer.setIdentityNumber(rs.getString("identification_number"));
                NameDAO nameimp = new NameImpl();
                Name name = nameimp.getName(rs.getInt("name_id"));
                customer.setName(name);
                LocationDAO locationimp = new LocationImpl();
                ArrayList<Location> listlocatino = locationimp.getLocationCustomer(rs.getInt("id"));
                customer.setLocation(listlocatino);
                InvoiceDAO invoiceDAO = new InvoiceImpl();
                ArrayList<Invoice> listinvoice = invoiceDAO.getInvoices(rs.getInt("id"));
                customer.setInvoice(listinvoice);
                PowerMeterDAO pDAO = new PowerMeterImpl();
                ArrayList<PoweMeter> listpower = pDAO.getMeters(rs.getInt("id"));
                customer.setMeters(listpower);
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
                customer.setIdentityNumber(rs.getString("identification_number"));
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
                customer.setIdentityNumber(rs.getString("identification_number"));
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

    @Override
    public ArrayList<Customer> getCustomersState(int stage) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select * from customers inner join names on customers.name_id = names.id where stage = ?"; 
        ArrayList<Customer> listcustomer = new ArrayList<>();
        try{
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, stage);
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

    @Override
    public ArrayList<Customer> getCustomerByState(int time_id, int state) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select customers.* from customers join power_meters on customers.id = power_meters.customer_id "
                + "join time on time.id = power_meters.time_id where time.id = ? and customers.stage = ?;";
        try{
            ArrayList<Customer> listCustomers = new ArrayList<>();
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setInt(1, time_id);
            ps.setInt(2, state);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setEmail(rs.getString("email"));
                c.setPhone_number(rs.getString("phone_number"));
                c.setStage(rs.getInt("stage"));
                c.setIdentityNumber(rs.getString("identification_number"));
                NameDAO nDAO = new NameImpl();
                Name name = nDAO.getName(rs.getInt("name_id"));
                c.setName(name);
                LocationDAO locationimp = new LocationImpl();
                ArrayList<Location> listlocatino = locationimp.getLocationCustomer(rs.getInt("id"));
                c.setLocation(listlocatino);
                listCustomers.add(c);
            }
            return listCustomers;
        }catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }
}
