/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.PoweMeter;
import java.util.ArrayList;
/**
 *
 * @author CongNguyen
 */
public interface PowerMeterDAO {
    public PoweMeter getMeter(int id);
    public ArrayList<PoweMeter> getMeters(int id);
}
