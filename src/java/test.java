

import Mail.MailUtil;
import dao.AccountDAO;
import dao.CustomerDAO;
import dao.InvoiceDAO;
import dao.LevelDAO;
import dao.MessageDAO;
import dao.PowerMeterDAO;
import dao.TimDAO;
import dao.implement.AccountImpl;
import dao.implement.CustomerImpl;
import dao.implement.InvoiceImpl;
import dao.implement.LevelImpl;
import dao.implement.MessageImpl;
import dao.implement.PowerMeterImpl;
import dao.implement.TimImpl;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import models.Account;
import models.Customer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.MessagingException;
import models.Invoice;
import models.Level;
import models.Messag;
import models.PoweMeter;
import models.Tim;
import org.mindrot.jbcrypt.BCrypt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CongNguyen
 */
public class test {
    public static void main(String[] args) throws MessagingException {
//        AccountDAO aDAO = new AccountImpl();
//        Account account = aDAO.getAccount("congnguyen");
//        System.out.println(account.getEmail());
//        System.out.println(account.getUsername());
//        System.out.println(account.getName().getLast_name());
//        for(int i = 0; i<account.getLocation().size(); i++) {
//            System.out.println(account.getLocation().get(i).getStreet());
//            System.out.println(account.getLocation().get(i).getWard());
//        }
        
        CustomerDAO cDAO = new CustomerImpl();
//        ArrayList<Customer> list = cDAO.getAllCustomers();
//        for(int i = 0; i<list.size();i++){
//            System.out.println(list.get(i).getName().getFirst_name());
//            System.out.println(list.get(i).getEmail());
//            for(int j = 0; j<list.get(i).getLocation().size();j++){
//                System.out.println(list.get(i).getLocation().get(j).getWard());
//            }
//        }
//        Customer customer = cDAO.getCustomer(5);
//        System.out.println(customer.getId());
//      
////        MessageDAO mDAO = new MessageImpl();
////        Messag m = new Messag();
////        m.setCustomer(customer);
////        m.setContent("test");
////        mDAO.storeMessage(m);
//        String password = "123456";
//        String hash = BCrypt.hashpw(password, BCrypt.gensalt());
//        System.out.println("BCrypt hash: " + hash);
//        String pas = "123456";
//        boolean valuate = BCrypt.checkpw(pas, account.getPassword());
//        System.out.println(valuate);
//        //$2a$10$/5ijjWN.5viyhstpJcfkpuhWo7H5Uzf.fFJnZpX7B3K1/7uoQvQC.
           ArrayList<Customer> list = cDAO.getCustomersNotPay();
           System.out.println(list.size());
//        TimDAO tDAO = new TimImpl();
//        Tim time = tDAO.getTime(1);
//        System.out.println(time.getName());
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        System.out.println(sdf.format(time.getEnd_date()));
//        LevelDAO lDAO = new LevelImpl();
//        Level level = lDAO.getLevel(1);
//        System.out.println(level.getName());
//        PowerMeterDAO pDAO = new PowerMeterImpl();
//        PoweMeter p = pDAO.getMeter(4);
//        System.out.println(p.getId());
//        for (int i = 0;i<p.getLevels().size();i++){
//            System.out.println(p.getLevels().get(i).getName());
//        }
//        
//        InvoiceDAO iDAO = new InvoiceImpl();
//        ArrayList<Invoice> listInvoice = iDAO.getInvoices(5);
//        for(int i = 0;i<listInvoice.size();i++){
//            System.out.println(listInvoice.get(i).getId());
//        }
          Customer c = cDAO.getCustomer(5);
          System.out.println(c.getMeters().size());
//          MailUtil.sendMail("congnx1998521@yahoo.com.vn", "thong bao", "Test");
          String m1 = "abc@gmail.com";
          String m2 = "asbd@gmail.com";
          String m3 = "asbd@gmail.com";
          String[] m = new String[3];
          m[0] = "asda@gmail.com";
          m[1] = "asd@gmail.com";
          m[2] = "congnx1998521@gmail.com";
          MailUtil.sendMail(m, "hello" , "content");
    }
}
