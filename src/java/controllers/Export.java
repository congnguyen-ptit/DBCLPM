/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static com.sun.faces.scripting.groovy.GroovyHelperFactory.createHelper;
import dao.CustomerDAO;
import dao.PowerMeterDAO;
import dao.implement.CustomerImpl;
import dao.implement.PowerMeterImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javafx.scene.control.Cell;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Customer;
import models.PoweMeter;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author CongNguyen
 */
@WebServlet(urlPatterns = {"/export"})
public class Export extends HttpServlet {
    private CustomerDAO cDAO = new CustomerImpl();
    private PowerMeterDAO pDAO = new PowerMeterImpl();
    ArrayList<Customer> listCustomers;
    ArrayList<PoweMeter> listMeters;
    private static String[] columns = {"#", "Họ tên", "Số CMND", "Số tiền", "Trạng thái", "Ngày đóng", "Địa chỉ"};
    double total;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String status = request.getParameter("status");
        String start_date = request.getParameter("start_date");
        String end_date = request.getParameter("end_date");
        if (status.equals("2")){
            listMeters = pDAO.getAll();
            write(listMeters);
        } 
        if (status.equals("0")) {
            listMeters = pDAO.getFromTo(start_date, end_date, 0);
            write(listMeters);
        }
        if (status.equals("1")){
            listMeters = pDAO.getFromTo(start_date, end_date, 1);
            write(listMeters);
        }
    } 
    public void write(ArrayList<PoweMeter> listpm) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Export report");
        Row row = sheet.createRow(0);
        for (int i=0;i<columns.length;i++){
            org.apache.poi.ss.usermodel.Cell cell = row.createCell(i);
            cell.setCellValue(columns[i]);
        }
        int rowNum = 1;
        int i = 0;
        for(PoweMeter pm : listpm) {
            i++;
            Row rowABC = sheet.createRow(rowNum++);
            rowABC.createCell(0).setCellValue(i);
            rowABC.createCell(1).setCellValue(pm.getCustomer().getName().toString());
            rowABC.createCell(2).setCellValue(pm.getCustomer().getIdentityNumber());
            rowABC.createCell(3).setCellValue(pm.getAmount());
            rowABC.createCell(4).setCellValue(pm.getCustomer().getStage());
            rowABC.createCell(5).setCellValue(pm.getStart_date());
            rowABC.createCell(6).setCellValue(pm.getCustomer().getLocation().toString());        
        }
        File file = new File("C:/demo/report.xls");
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }
}
