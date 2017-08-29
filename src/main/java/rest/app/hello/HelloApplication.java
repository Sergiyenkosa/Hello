package rest.app.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class HelloApplication {

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("contacts");
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}
}