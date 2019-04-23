package ai.clova.practice.config.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.ConnectionString;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@Configuration
@EnableConfigurationProperties(MultipleMongoProperties.class)
@EnableReactiveMongoRepositories(basePackages = "ai.clova.practice.repository")
public class MongoUserConfig extends AbstractReactiveMongoConfiguration {

	@Autowired
	private MultipleMongoProperties mongoProperties;

	@Bean("userMongoDbFactory")
	public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory() throws Exception {
		final MongoProperties userMongoProperties = this.mongoProperties.getMongoTest();

		final String uri = userMongoProperties.getUri();
		final ConnectionString connectionString = new ConnectionString(uri);

		return new SimpleReactiveMongoDatabaseFactory(connectionString);
	}

	@Bean("userMongoOperations")
	public ReactiveMongoOperations reactiveMongoOperations(
		@Qualifier("userMongoDbFactory") final ReactiveMongoDatabaseFactory mongoDbFactory) {
		return new ReactiveMongoTemplate(mongoDbFactory);
	}

	@Override
	protected String getDatabaseName() {
		return mongoProperties.getMongoTest().getDatabase();
	}

	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create(mongoProperties.getMongoTest().getUri());
	}

	@Bean("reactiveMongoTemplate")
	public ReactiveMongoTemplate reactiveMongoTemplate(
		@Qualifier("userMongoDbFactory") ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory) {
		return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory);
	}
}
