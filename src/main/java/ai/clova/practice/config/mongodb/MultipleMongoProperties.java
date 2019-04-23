package ai.clova.practice.config.mongodb;

import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "mongodb")
@Data
public class MultipleMongoProperties {
	private MongoProperties mongoTest;
}
