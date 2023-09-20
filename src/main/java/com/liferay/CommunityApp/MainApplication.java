package com.liferay.CommunityApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication//(exclude = {SecurityAutoConfiguration.class })
@RestController
public class MainApplication {

	public static void main(String[] args) {SpringApplication.run(MainApplication.class, args);}
	public String index(){
		return "Hello World";
	}
}
