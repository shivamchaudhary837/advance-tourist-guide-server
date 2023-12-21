package com.capgemini.ocean.institute.training;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.capgemini.ocean.institute.training.service.AdminService;

@SpringBootApplication
public class AdvancedTouristGuideAdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedTouristGuideAdminServiceApplication.class, args);
	}

	
//	@Bean
//    public CommandLineRunner initializeDefaultAdmins(AdminService adminService) {
//        return args -> adminService.initializeDefaultAdmins();
//    }
}
