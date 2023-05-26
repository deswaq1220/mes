package mes.smartmes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;


@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})

public class SmartmesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartmesApplication.class, args);

	}

}
