/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasu.SocialApp.social;

import com.vasu.SocialApp.dao.AppUserDAO;
import com.vasu.SocialApp.entity.AppUser;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;

/**
 *
 * @author rv
 */
public class ConnectionSignUpImpl implements ConnectionSignUp {

   private AppUserDAO appUserDAO;
 
    public ConnectionSignUpImpl(AppUserDAO appUserDAO) {
        this.appUserDAO = appUserDAO;
    }
 
    // After logging in social networking.
    // This method will be called to create a corresponding App_User record
    // if it does not already exist.
    @Override
    public String execute(Connection<?> connection) {
 
        AppUser account = appUserDAO.createAppUser(connection);
        return account.getUserName();
    }
}
