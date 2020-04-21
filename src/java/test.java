
import dao.AccountDAO;
import dao.CustomerDAO;
import dao.implement.AccountImpl;
import dao.implement.CustomerImpl;
import models.Account;
import models.Customer;
import java.util.ArrayList;

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
    public static void main(String[] args) {
        AccountDAO aDAO = new AccountImpl();
        Account account = aDAO.getAccount("congnguyen");
        System.out.println(account.getEmail());
        System.out.println(account.getUsername());
        System.out.println(account.getName().getLast_name());
        for(int i = 0; i<account.getLocation().size(); i++) {
            System.out.println(account.getLocation().get(i).getStreet());
            System.out.println(account.getLocation().get(i).getWard());
        }
        
        CustomerDAO cDAO = new CustomerImpl();
        ArrayList<Customer> list = cDAO.getAllCustomers();
        for(int i = 0; i<list.size();i++){
            System.out.println(list.get(i).getName().getFirst_name());
            System.out.println(list.get(i).getEmail());
            for(int j = 0; j<list.get(i).getLocation().size();j++){
                System.out.println(list.get(i).getLocation().get(j).getWard());
            }
        }
    }
}
