package ai.clova.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import ai.clova.practice.handler.HelloHandler;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {
	@Bean
	public RouterFunction<ServerResponse> helloRouter(HelloHandler helloHandler) {
		return RouterFunctions.route(RequestPredicates.GET("/hello"), helloHandler::handleDefaultRequest)
							  .andRoute(RequestPredicates.GET("/hello2"), helloHandler::handleMoreRequest)
							  .andRoute(RequestPredicates.GET("/add"), helloHandler::handleAddingWorkers);
	}
}
