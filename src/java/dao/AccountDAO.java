/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Account;

/**
 *
 * @author CongNguyen
 */
public interface AccountDAO {
    public Account getAccount(String username);
    public boolean checkLogin(String username, String password);
}
