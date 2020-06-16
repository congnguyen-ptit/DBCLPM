/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import dao.LevelDAO;
import dao.implement.LevelImpl;
import java.io.Serializable;
import java.sql.Date;
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
    private Date start_date;
    private ArrayList<Level> levels;

    public PoweMeter() {
    }
    public long getId() {
        return id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
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
    
    public double getAmount() {
        int index= this.getNew_index() - this.getOld_index() + 1;
        double amount = 0;
        LevelDAO lDAO = new LevelImpl();
        ArrayList<Level> listLevels = lDAO.getAll();
        int bac1 = listLevels.get(0).getEnd_index() - listLevels.get(0).getStart_index();
        int bac2 = listLevels.get(1).getEnd_index() - listLevels.get(1).getStart_index() + 1;
        int bac3 = listLevels.get(2).getEnd_index() - listLevels.get(2).getStart_index() + 1;
        int bac4 = listLevels.get(3).getEnd_index() - listLevels.get(3).getStart_index() + 1;
        int bac5 = listLevels.get(4).getEnd_index() - listLevels.get(4).getStart_index() + 1;
        int bac6 = listLevels.get(5).getEnd_index() - listLevels.get(5).getStart_index() + 1;
        double dongian1 = listLevels.get(0).getPrice();
        double dongian2 = listLevels.get(1).getPrice();
        double dongian3 = listLevels.get(2).getPrice();
        double dongian4 = listLevels.get(3).getPrice();
        double dongian5 = listLevels.get(4).getPrice();
        double dongian6 = listLevels.get(5).getPrice();
        int dinhmuc1 = listLevels.get(0).getEnd_index();
        int dinhmuc2 = listLevels.get(1).getEnd_index();
        System.out.println(dinhmuc2);
        int dinhmuc3 = listLevels.get(2).getEnd_index();
        int dinhmuc4 = listLevels.get(3).getEnd_index();
        int dinhmuc5 = listLevels.get(4).getEnd_index();
        int dinhmuc6 = listLevels.get(5).getEnd_index();
        if (index == 0 ){
            amount = 0;
        }
        else if (index > 0 && index <= dinhmuc1) {
            amount = index * dongian1;
        }else if (index > dinhmuc1 && index <= dinhmuc2) {
            amount = bac1*dongian1 + (index - bac1)*dongian2;
        } else if (index > dinhmuc2 && index <= dinhmuc3) {
            amount = bac1*dongian1 + bac2*dongian2 + (index - bac1 - bac2)*dongian3;
        } else if (index > dinhmuc3 && index <= dinhmuc4) {
            amount = bac1*dongian1 + bac2*dongian2 + bac3*dongian3 + (index - bac1 - bac2 - bac3)*dongian4;
        } else if (index > dinhmuc4 && index <= dinhmuc5) {
            amount = bac1*dongian1 + bac2*dongian2 + bac3*dongian3 + bac4*dongian4 + (index - bac1 - bac2 - bac3 - bac4)*dongian5;
        } else if (index > dinhmuc5) {
            amount = bac1*dongian1 + bac2*dongian2 + bac3*dongian3 + bac4*dongian4 + + bac5*dongian5 + (index - bac1 - bac2 - bac3 - bac4 - bac5)*dongian6;
        }
        return amount;
    } 
}
