/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;


/**
 *
 * @author CongNguyen
 */
public class Level implements Serializable{
    private static long serialVersionUID = 1L;
    private int id;
    private String name;
    private int start_index;
    private int end_index;
    private float price;
    private ArrayList<PoweMeter> powerMeters;

    public Level() {
    }

    public static void setSerialVersionUID(long serialVersionUID) {
        Level.serialVersionUID = serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStart_index() {
        return start_index;
    }

    public void setStart_index(int start_index) {
        this.start_index = start_index;
    }

    public int getEnd_index() {
        return end_index;
    }

    public void setEnd_index(int end_index) {
        this.end_index = end_index;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<PoweMeter> getPowerMeters() {
        return powerMeters;
    }

    public void setPowerMeters(ArrayList<PoweMeter> powerMeters) {
        this.powerMeters = powerMeters;
    }
    
    
}
