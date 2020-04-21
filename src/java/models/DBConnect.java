/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author CongNguyen
 */
public class DBConnect {
    static java.sql.Connection conn = null;
    private static String username = "sa";
    private static String password = "1234";
    private static String DBname = "SQA";
    private static String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=SQA;useUnicode=true;characterEncoding=UTF-8;";
    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static Connection getDBConnection(){
        try{
            Class.forName(driver);
            try{
               conn = DriverManager.getConnection(url,username, password);
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    public static void dbClose(){
        try{
            conn.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
