/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Messag;

/**
 *
 * @author CongNguyen
 */
public interface MessageDAO {
    public void storeMessage(Messag message);
}
