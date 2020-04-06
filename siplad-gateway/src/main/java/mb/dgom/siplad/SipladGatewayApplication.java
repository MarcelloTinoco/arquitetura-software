package mb.dgom.siplad;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients
public class SipladGatewayApplication {

	@Value("${spring.application.name}") 
	private String applicationName;
	
	public static void main(String[] args) {
		SpringApplication.run(SipladGatewayApplication.class, args);
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
