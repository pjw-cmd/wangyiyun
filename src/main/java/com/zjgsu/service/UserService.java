package com.zjgsu.service;

import com.zjgsu.dao.UserDao;
import com.zjgsu.entity.UserEntity;
import com.zjgsu.utils.IDGenerator;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public boolean login(String userName, String userPassword) {
        UserEntity userEntity = userDao.getByCriterion(Restrictions.eq("userName", userName));
        if (userEntity != null) {
            return userPassword.equals(userEntity.getUserPassword());
        }

        return false;
    }

    public String register(String userName, String userPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(IDGenerator.generateID());
        userEntity.setUserName(userName);
        userEntity.setUserPassword(userPassword);
        String userId = userDao.save(userEntity);
        System.out.println(userId);
        return userEntity.getUserId();
    }


}
