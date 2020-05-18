/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.implement;

import dao.MessageDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import models.DBConnect;
import models.Messag;

/**
 *
 * @author CongNguyen
 */
public class MessageImpl implements MessageDAO {

    @Override
    public void storeMessage(Messag message) {
        Connection conn = DBConnect.getDBConnection();
        String sql = "insert into messages(customer_id, content, created_at) values (?,?,GETDATE())";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setLong(1, message.getCustomer().getId());
            ps.setString(2, message.getContent());
            ps.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
            DBConnect.dbClose();
        }
        DBConnect.dbClose();
    }
}