package com.alibaba.dubbo.demo.pp;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.unj.dubbotest.mobel.User;
import com.unj.dubbotest.provider.DemoService;
import com.unj.dubbotest.provider.UserService;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;

public class APIConsumer {

	public static void main(String[] args) throws Exception {
		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName("api-consumer");

		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://localhost:2181");

		// 注意：ReferenceConfig为重对象，内部封装了与注册中心的连接，以及与服务提供方的连接

		// 引用远程服务
		ReferenceConfig<UserService> reference = new ReferenceConfig<UserService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference.setApplication(application);
		reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference.setInterface(UserService.class);

		// 和本地bean一样使用xxxService
		UserService userService = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用

		User s = userService.getUser(123);
		System.out.println(s);



		// 引用远程服务
		ReferenceConfig<DemoService> reference2 = new ReferenceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
		reference2.setApplication(application);
		reference2.setRegistry(registry); // 多个注册中心可以用setRegistries()
		reference2.setInterface(DemoService.class);

		DemoService demoService = reference2.get();

		demoService.sayHello("sds");
	}
}