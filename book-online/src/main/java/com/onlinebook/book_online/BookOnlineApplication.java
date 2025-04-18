package com.onlinebook.book_online;

import com.onlinebook.book_online.models.Role;
import com.onlinebook.book_online.repository.RoleRepository;
import com.onlinebook.book_online.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class BookOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookOnlineApplication.class, args);
	}

	@Bean
	public  CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(Role.builder().name("USER").build());
			}
		};
	}
}
