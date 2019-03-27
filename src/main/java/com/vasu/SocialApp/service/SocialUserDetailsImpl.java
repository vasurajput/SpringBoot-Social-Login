/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasu.SocialApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Service;

/**
 *
 * @author rv
 */
@Service
public class SocialUserDetailsImpl implements SocialUserDetailsService {

    @Autowired
    private UserDetailsService userDetailService;

    // After user created by ConnectionSignUpImpl.execute(Connection<?>)
    // This method is called by the Spring Social API.
    @Override
    public SocialUserDetails loadUserByUserId(String userName) throws UsernameNotFoundException, DataAccessException {

        System.out.println("SocialUserDetailsServiceImpl.loadUserByUserId=" + userName);

        // See UserDetailServiceImpl.
        UserDetails userDetails = ((UserDetailsServiceImpl) userDetailService).loadUserByUsername(userName);

        return (SocialUserDetails) (SocialUserDetailsImpl) userDetails;
    }

}
