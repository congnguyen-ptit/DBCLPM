/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Tim;
import java.util.ArrayList;
/**
 *
 * @author CongNguyen
 */
public interface TimDAO {
    public Tim getTime(int id);
    public ArrayList<Tim> getAll(int id);
    public ArrayList<Tim> getAllTimes();
}
