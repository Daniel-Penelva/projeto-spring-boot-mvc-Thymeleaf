package projeto.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = "projeto.springboot.model")
@ComponentScan(basePackages = {"projeto.*"})
public class ProjetospringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetospringbootApplication.class, args);
	}

}
