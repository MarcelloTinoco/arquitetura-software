package mb.dgom.siplad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
//Como as classes do pacote core estao em um pacote diferente das classes deste app, devemos informara ao Spring para escanear os componentes que estao no pacote mb.dgom.siplad.core
//@ComponentScan(basePackages = {"mb.dgom.siplad.core"} )  
public class SipladServiceTesteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SipladServiceTesteApplication.class, args);
	}

}
