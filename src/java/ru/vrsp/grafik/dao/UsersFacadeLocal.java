/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.vrsp.grafik.dao;

import java.util.List;
import javax.ejb.Local;
import ru.vrsp.grafik.entity.Users;

/**
 *
 * @author user
 */
@Local
public interface UsersFacadeLocal {

//    void create(Users users);
//
//    void edit(Users users);
//
//    void remove(Users users);
//
    Users find(Object id);
    List<Users> findAll();
//
//    List<Users> findAll();
//
//    List<Users> findRange(int[] range);
//
//    int count();
    Users userLogin(String userName, String userPass);
}
