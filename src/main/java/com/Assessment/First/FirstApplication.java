package com.Assessment.First;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
@EnableWebMvc
@EnableScheduling


public class FirstApplication {

	public static void main(String[] args) {

		SpringApplication.run(FirstApplication.class, args);
	}
//	@Bean
//	public CommandLineRunner createDefaultAdmin(SocialUserRepository socialUserRepository, PasswordEncoder passwordEncoder) {
//		return args -> {
//			if (socialUserRepository.count() == 0) { // Check if users exist
//				SocialUser admin = new SocialUser();
//				admin.setEmail("admin@avaali.com");
//				admin.setPassword(passwordEncoder.encode("admin@123")); // Hash password
//
//				socialUserRepository.save(admin);
//			}
//		};
//	}


}
