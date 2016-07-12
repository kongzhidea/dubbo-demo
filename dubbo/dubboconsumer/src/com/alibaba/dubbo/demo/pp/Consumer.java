package com.alibaba.dubbo.demo.pp;

import com.unj.dubbotest.mobel.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.rpc.RpcContext;
import com.unj.dubbotest.provider.UserService;

public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                new String[]{"applicationContext.xml"});
        context.start();

        System.out.println("client init!");

        UserService userService = (UserService) context.getBean("userService");

        System.out.println("bean init!");

        while (true) {
            try {

                int x = 0;
                while (true) {

                    // EchoService echoService = (EchoService) userService; //
                    // 强制转型为EchoService
                    //
                    // Object status = echoService.$echo("OK"); // 回声测试可用性
                    // System.out.println(status);

                    RpcContext.getContext().setAttachment("index", "" + (++x));
                    User u = userService.getUser(x);
                    System.out.println(u);

                    String serverIP = RpcContext.getContext().getRemoteHost(); // 获取最后一次调用的提供方IP地址
                    System.out.println(u + ",serverIP=" + serverIP);
                    // List<User> list = demoService.getUsers();
                    // if (list != null && list.size() > 0) {
                    // for (int i = 0; i < list.size(); i++) {
                    // System.out.println(list.get(i));
                    // }
                    // }
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }

        // System.in.read();
    }
}