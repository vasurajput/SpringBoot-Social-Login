/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vasu.SocialApp.dao;

import com.vasu.SocialApp.entity.UserConnection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rv
 */
@Repository
@Transactional
public class UserConnectionDAO {

     @Autowired
    private EntityManager entityManager;
 
    public UserConnection findUserConnectionByUserProviderId(String userProviderId) {
        try {
            String sql = "Select e from " + UserConnection.class.getName() + " e " //
                    + " Where e.userProviderId = :userProviderId ";
 
            Query query = entityManager.createQuery(sql, UserConnection.class);
            query.setParameter("userProviderId", userProviderId);
 
            List<UserConnection> list = query.getResultList();
 
            return list.isEmpty() ? null : list.get(0);
        } catch (NoResultException e) {
            return null;
        }
    }
}
