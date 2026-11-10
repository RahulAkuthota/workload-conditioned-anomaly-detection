package com.research.userservice.dao.impl;

import com.research.userservice.dao.UserDao;
import com.research.userservice.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {

        entityManager.persist(user);
    }

    @Override
    public User getUserById(Long userId) {

        return entityManager.find(User.class, userId);
    }

    @Override
    public List<User> getAllUsers() {

        String hql = "FROM User";

        return entityManager.createQuery(hql, User.class)
                .getResultList();
    }
}