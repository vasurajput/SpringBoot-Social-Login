/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasu.SocialApp.service;

import com.vasu.SocialApp.dao.AppRoleDAO;
import com.vasu.SocialApp.dao.AppUserDAO;
import com.vasu.SocialApp.social.SocialUserDetailsImpl;
import com.vasu.SocialApp.entity.AppUser;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author rv
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AppUserDAO appUserDAO;
  
    @Autowired
    private AppRoleDAO appRoleDAO;
  
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
  
        System.out.println("UserDetailsServiceImpl.loadUserByUsername=" + userName);
  
        AppUser appUser = this.appUserDAO.findAppUserByUserName(userName);
  
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
  
        System.out.println("Found User: " + appUser);
  
        // [ROLE_USER, ROLE_ADMIN,..]
        List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());
  
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
  
        SocialUserDetailsImpl userDetails = new SocialUserDetailsImpl(appUser, roleNames);
  
        return userDetails;
    }
}
