package lt.Karolis.MovieReviewTest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MovieReviewTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieReviewTestApplication.class, args);
	}

}
