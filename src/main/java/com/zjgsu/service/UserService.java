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

    public String login(String userName, String userPassword) {
        UserEntity existUserEntity = userDao.getByCriterion(Restrictions.eq("userName", userName));
        if (existUserEntity != null) {
            if (userPassword.equals(existUserEntity.getUserPassword())) {
                return existUserEntity.getUserId();
            }
            return null;
        }

        return null;
    }

    public String register(String userName, String userPassword) {
        UserEntity existUserEntity = userDao.getByCriterion(Restrictions.eq("userName", userName));
        if (existUserEntity == null) {
            // 没注册过
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(IDGenerator.generateID());
            userEntity.setUserName(userName);
            userEntity.setUserPassword(userPassword);
            return userDao.save(userEntity);
        } else {
            return null;
        }

    }


}
