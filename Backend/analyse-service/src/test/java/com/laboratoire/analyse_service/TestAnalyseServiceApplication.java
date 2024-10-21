package com.laboratoire.analyse_service;

import org.springframework.boot.SpringApplication;

public class TestAnalyseServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(AnalyseServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
