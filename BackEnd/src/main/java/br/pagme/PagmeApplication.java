package br.pagme;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EntityScan(basePackages = {"br.pagme.*"})
@EnableJpaRepositories(basePackages = {"br.pagme.*"})
@EnableTransactionManagement
@SpringBootApplication
public class PagmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(PagmeApplication.class, args);
	}

}
