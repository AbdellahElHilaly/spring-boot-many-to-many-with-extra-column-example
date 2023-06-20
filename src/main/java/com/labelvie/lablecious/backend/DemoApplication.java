package com.labelvie.lablecious.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}

/*

INSERT INTO users (id,email, fname, img, job, lname, num, role)
VALUES (1,'example@example.com', 'John', 'image.jpg', 'Engineer', 'Doe', '1234567890', 1);

 */