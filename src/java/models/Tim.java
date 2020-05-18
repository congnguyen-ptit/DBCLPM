/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author CongNguyen
 */
public class Tim implements Serializable{
    private static final long serialVersionUID = 1L;
    private String name; 
    private int id;
    private ArrayList<PoweMeter> powerMeter;
    private Date start_date;
    private Date end_date;
    public Tim(){
        
    }

    public ArrayList<PoweMeter> getPowerMeter() {
        return powerMeter;
    }

    public void setPowerMeter(ArrayList<PoweMeter> powerMeter) {
        this.powerMeter = powerMeter;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
    
    
    
}
