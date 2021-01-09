package com.meetapp.meetapp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class CrimelessApplication {

	public static Logger logger = LogManager.getLogger();


	public static void main(String[] args) {
		logger.info("start");
		logger.debug("start debug");
		logger.info(logger.getLevel());
		SpringApplication.run(CrimelessApplication.class, args);
	}

}
