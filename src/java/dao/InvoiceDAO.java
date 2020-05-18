/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Invoice;
import java.util.ArrayList;
/**
 *
 * @author CongNguyen
 */
public interface InvoiceDAO {
    public Invoice getInvoice(int id);
    public ArrayList<Invoice> getInvoices(int id);
}
