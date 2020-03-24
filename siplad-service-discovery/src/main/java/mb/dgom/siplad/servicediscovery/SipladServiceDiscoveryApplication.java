package mb.dgom.siplad.servicediscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class SipladServiceDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SipladServiceDiscoveryApplication.class, args);
	}

}
