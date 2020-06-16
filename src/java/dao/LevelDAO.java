/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Level;
import java.util.ArrayList;
/**
 *
 * @author CongNguyen
 */
public interface LevelDAO {
    public Level getLevel(int id);
    public ArrayList<Level> getLevels(int id);
    public ArrayList<Level> getAll();
    public boolean updateLevel(Level level);
}
