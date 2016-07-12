package com.unj.dubbotest.provider.impl;

import com.alibaba.dubbo.rpc.RpcContext;
import com.unj.dubbotest.mobel.User;
import com.unj.dubbotest.provider.UserService;

public class UserServiceImpl implements UserService {

    public User getUser(int id) {
        String idx = RpcContext.getContext().getAttachment("index");
        String clientIP = RpcContext.getContext().getRemoteHost(); // 获取调用方IP地址

        User user = new User();
        user.setId(id);
        user.setAge(id + 10);
        user.setName("n:" + id);
        user.setSex(String.valueOf(id % 2));
        user.setHeadUrl("headurl." + id);

        System.out.println("UserService:" + id + ",clientIP=" + clientIP
                + ",idx=" + idx + ",user=" + user);
        return user;
    }

}
