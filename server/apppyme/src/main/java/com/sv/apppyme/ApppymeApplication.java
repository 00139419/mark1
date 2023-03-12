package com.sv.apppyme;


import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApppymeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApppymeApplication.class, args);
		String logsProperties = "/Users/dm420/Documents/git/mark1/server/apppyme/src/main/resources/log4j.properties";
		PropertyConfigurator.configure(logsProperties);
	}

}

