package com.zjgsu.dao.impl;


import com.zjgsu.entity.UserEntity;


import org.springframework.stereotype.Repository;

import com.zjgsu.dao.UserDao;

@Repository("UserDao")
public class UserDaoImpl extends BaseDaoImpl<UserEntity> implements UserDao {

}