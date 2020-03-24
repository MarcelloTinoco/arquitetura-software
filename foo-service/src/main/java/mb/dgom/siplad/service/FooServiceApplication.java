package mb.dgom.siplad.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FooServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FooServiceApplication.class, args);
	}

}
