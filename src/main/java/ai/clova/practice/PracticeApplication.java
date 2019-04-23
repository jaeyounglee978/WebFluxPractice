package ai.clova.practice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication(
	exclude = {MongoAutoConfiguration.class,
		MongoDataAutoConfiguration.class,
		MongoReactiveDataAutoConfiguration.class})
public class PracticeApplication {
	public static void main(String[] args) {
		SpringApplication.run(PracticeApplication.class, args);
	}
}
