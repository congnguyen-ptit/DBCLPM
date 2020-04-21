/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Customer;
import java.util.ArrayList;

/**
 *
 * @author CongNguyen
 */
public interface CustomerDAO {
    public Customer getCustomer(int id);
    public ArrayList<Customer> getAllCustomers();
    public ArrayList<Customer> getCustomersByName(String name);
}
