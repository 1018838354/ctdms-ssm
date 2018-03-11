package cn.jxufe.ctdms;

import cn.jxufe.ctdms.util.storage.StorageProperties;
import cn.jxufe.ctdms.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring/spring-*.xml"})
@EnableConfigurationProperties(StorageProperties.class)
public class CtdmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtdmsApplication.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			//storageService.deleteAll();
			storageService.init();
		};
	}
}
