package com.shopspace.shopspaceadminservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.shopspace.shopspaceadminservice.client")
@EnableDiscoveryClient
public class ShopspaceAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopspaceAdminServiceApplication.class, args);
	}

}
