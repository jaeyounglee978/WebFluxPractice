package ai.clova.practice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import ai.clova.practice.handler.FunctionalHandler;
import ai.clova.practice.handler.GetHandler;

@Configuration
@EnableWebFlux
public class WebFluxConfig implements WebFluxConfigurer {
	@Bean
	public RouterFunction<ServerResponse> helloRouter(FunctionalHandler helloHandler) {
		return RouterFunctions.route(RequestPredicates.GET("/functional/1"), helloHandler::handleDefaultRequest)
							  .andRoute(RequestPredicates.GET("/functional/json"), helloHandler::getJson)
							  .andRoute(RequestPredicates.GET("/functional/{pathVar}"), helloHandler::handleMoreRequest);
	}

	@Bean
	RouterFunction<ServerResponse> accumulateRouter(GetHandler getHandler) {
		return RouterFunctions.route(RequestPredicates.GET("/getData"), getHandler::getLaborTimeDiff);
	}
}
