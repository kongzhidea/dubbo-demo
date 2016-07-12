package com.unj.dubbotest.provider.impl;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.unj.dubbotest.provider.DemoService;
import com.unj.dubbotest.provider.UserService;

public class APIProvider {

	public static void main(String[] args) throws Exception {
		// 服务实现
		UserService xxxService = new UserServiceImpl();

		DemoService demoService = new DemoServiceImpl();

		// 当前应用配置
		ApplicationConfig application = new ApplicationConfig();
		application.setName("xxx");

		// 连接注册中心配置
		RegistryConfig registry = new RegistryConfig();
		registry.setAddress("zookeeper://localhost:2181");

		// 服务提供者协议配置
		ProtocolConfig protocol = new ProtocolConfig();
		protocol.setName("dubbo");
		protocol.setPort(12345);
		protocol.setThreads(200);

		// 注意：ServiceConfig为重对象，内部封装了与注册中心的连接，以及开启服务端口

		// 服务提供者暴露服务配置
		ServiceConfig<UserService> service = new ServiceConfig<UserService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
		service.setApplication(application);
		service.setRegistry(registry); // 多个注册中心可以用setRegistries()
		service.setProtocol(protocol); // 多个协议可以用setProtocols()
		service.setInterface(UserService.class);
		service.setRef(xxxService);

		// 暴露及注册服务
		service.export();


		ServiceConfig<DemoService> service2 = new ServiceConfig<DemoService>(); // 此实例很重，封装了与注册中心的连接，请自行缓存，否则可能造成内存和连接泄漏
		service2.setApplication(application);
		service2.setRegistry(registry); // 多个注册中心可以用setRegistries()
		service2.setProtocol(protocol); // 多个协议可以用setProtocols()
		service2.setInterface(DemoService.class);
		service2.setRef(demoService);

		// 暴露及注册服务
		service2.export();

		System.out.println("start");
		System.in.read(); // 为保证服务一直开着，利用输入流的阻塞来模拟
	}
}