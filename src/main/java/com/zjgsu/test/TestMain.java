//package com.zjgsu.test;
//
//
//import com.zjgsu.entity.UserEntity;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.cfg.Configuration;
//
//public class TestMain {
//    public static void main(String[] args) {
//        Configuration cfg = new Configuration();
//        cfg.configure();
//        SessionFactory sessionFactory = cfg.buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
//        UserEntity userEntity = new UserEntity();
//        userEntity.setName("asd");
//        userEntity.setPasswd("12356");
//        session.save(userEntity);
//        transaction.commit();
//        session.close();
//        sessionFactory.close();
//    }
//}
