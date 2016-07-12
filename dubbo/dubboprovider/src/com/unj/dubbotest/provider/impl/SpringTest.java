package com.unj.dubbotest.provider.impl;

import com.unj.dubbotest.provider.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml"});
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService.getUser(1));

    }
}