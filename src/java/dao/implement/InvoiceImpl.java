/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.CustomerDAO;
import dao.InvoiceDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Customer;
import models.DBConnect;
import models.Invoice;

/**
 *
 * @author CongNguyen
 */
public class InvoiceImpl implements InvoiceDAO{

    @Override
    public Invoice getInvoice(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select * from invoices where id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Invoice invoice = new Invoice();
            while(rs.next()) {
                invoice.setId(rs.getInt("id"));
                CustomerDAO cDAO = new CustomerImpl();
                Customer c = cDAO.getCustomer(rs.getInt("customer_id"));
                invoice.setCustomer(c);
            }
            return invoice;
        } catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }

    @Override
    public ArrayList<Invoice> getInvoices(int id) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "select * from invoices where customer_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            ArrayList<Invoice> listinvoice = new ArrayList<>();
            while(rs.next()) {
                Invoice invoice = new Invoice();
                invoice.setId(rs.getInt("id"));
                listinvoice.add(invoice);
            }
            return listinvoice;
        } catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
        return null;
    }
    
}
