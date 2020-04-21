/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import models.Location;

/**
 *
 * @author CongNguyen
 */
public interface LocationDAO {
    public ArrayList<Location> getLocationAccount(int id);
    public ArrayList<Location> getLocationCustomer(int id);
}
