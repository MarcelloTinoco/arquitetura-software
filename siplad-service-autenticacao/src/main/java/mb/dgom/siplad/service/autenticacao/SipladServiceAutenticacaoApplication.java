package mb.dgom.siplad.service.autenticacao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SipladServiceAutenticacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SipladServiceAutenticacaoApplication.class, args);
	}

}
