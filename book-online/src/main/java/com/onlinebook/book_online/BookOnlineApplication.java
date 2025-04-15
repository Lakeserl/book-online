package com.onlinebook.book_online;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BookOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookOnlineApplication.class, args);
	}

}
