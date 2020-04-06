package mb.dgom.siplad.security.web;

import org.springframework.stereotype.Component;

@Component
public class ThreadLocalJWTTokenUtil {

	public ThreadLocal<String> context = new ThreadLocal<String>();
	
	public ThreadLocalJWTTokenUtil() {
		System.out.println("ThreadLocalJWTTokenUtil");
	}
	
	public void setToken(String token) {
		this.context.set(token);
	}
	
	public String getToken() {
		
		return this.context.get();
	}
	
	public void removeToken() {
		this.context.remove();
	}
	
}
