package mkanak_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })

public class MkanakSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MkanakSpringApplication.class, args);
	}

}
