package mb.dgom.siplad.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
@EnableDiscoveryClient
public class FooServiceApplication {

	@Value("${spring.application.name}") 
	private String applicationName;
	
	public static void main(String[] args) {
		SpringApplication.run(FooServiceApplication.class, args);
	}

	/**
	 * Customizar as metricas que serao expostas pelo prometheus com o nome da aplicacao registrada
	 * @return
	 */
	@Bean
	MeterRegistryCustomizer<MeterRegistry> configurer(){
		return (registry) -> registry.config().commonTags("application", applicationName);
	}
}
