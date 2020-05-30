package com.zjgsu.service;

import com.zjgsu.dao.UserDao;
import com.zjgsu.entity.UserEntity;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    public boolean register(String userName, String userPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setUserPassword(userPassword);
        String save = userDao.save(userEntity);
        return save != null;
    }


}
