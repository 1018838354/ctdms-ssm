package cn.jxufe.ctdms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(locations = {"classpath:spring/spring-*.xml"})
public class CtdmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtdmsApplication.class, args);
	}
}
