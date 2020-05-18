/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.ArrayList;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author CongNguyen
 */
public class PoweMeter implements Serializable {
    private static final long serialVersionUID = 1L;
    @Min(0)
    private long id;
    @NotEmpty
    private Customer customer;
    private int old_index, new_index;
    private Tim time;
    private ArrayList<Level> levels;

    public PoweMeter() {
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOld_index() {
        return old_index;
    }

    public void setOld_index(int old_index) {
        this.old_index = old_index;
    }

    public int getNew_index() {
        return new_index;
    }

    public void setNew_index(int new_index) {
        this.new_index = new_index;
    }

    public Tim getTime() {
        return time;
    }

    public void setTime(Tim time) {
        this.time = time;
    }

    public ArrayList<Level> getLevels() {
        return levels;
    }

    public void setLevels(ArrayList<Level> levels) {
        this.levels = levels;
    }
    
    
    
    
}
