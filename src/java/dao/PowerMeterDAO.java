/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Date;
import models.PoweMeter;
import java.util.ArrayList;
/**
 *
 * @author CongNguyen
 */
public interface PowerMeterDAO {
    public PoweMeter getMeter(int id);
    public ArrayList<PoweMeter> getMeters(int id);
    public ArrayList<PoweMeter> getAll();
    public double getTotal(ArrayList<PoweMeter> listOb );
    public ArrayList<PoweMeter> getFromTo(String from, String to, int status);
    public ArrayList<PoweMeter> getFromToNoStatus(String from, String to);
}
