package com.zjgsu.service;

import com.zjgsu.dao.UserDao;
import com.zjgsu.entity.UserEntity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserDao userDao;

    public boolean login(String user_id, String user_passwd) {
        UserEntity userEntity = userDao.getById(user_id);
        if (userEntity != null) {
            return user_passwd.equals(userEntity.getUserPassword());
        }

        return false;
    }


}
