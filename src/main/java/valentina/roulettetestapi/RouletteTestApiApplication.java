package valentina.roulettetestapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import valentina.roulettetestapi.controller.RouletteController;

@SpringBootApplication
public class RouletteTestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RouletteTestApiApplication.class, args);
	}

}
