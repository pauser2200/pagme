package com.br.pagme;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EntityScan(basePackages = {"com.br.*"})
@EnableJpaRepositories(basePackages = {"com.br.pagme.*"})
@EnableTransactionManagement
@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class PagmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagmeApplication.class, args);
	}

}
