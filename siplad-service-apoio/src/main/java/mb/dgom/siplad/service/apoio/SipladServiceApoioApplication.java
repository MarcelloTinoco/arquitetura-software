package mb.dgom.siplad.service.apoio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
public class SipladServiceApoioApplication {

	@Value("${spring.application.name}") 
	private String applicationName;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SipladServiceApoioApplication.class, args);
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
