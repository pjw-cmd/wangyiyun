//package com.zjgsu.test;
//import org.hibernate.Session;
//
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.zjgsu.entity.User;
//
//
//public class test {
//    @Test
//    public void test2() {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("spring-datasource.xml");
//        SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
//        Session session = sessionFactory.openSession();//从会话工厂获取一个session
//
//        Transaction transaction = session.beginTransaction();//开启一个新的事务
//        User user = new User();
//        user.setName("hello spring");
//        session.save(user);
//        transaction.commit();//提交事务
//    }
//}