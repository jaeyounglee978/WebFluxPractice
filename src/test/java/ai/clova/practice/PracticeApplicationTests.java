package ai.clova.practice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import ai.clova.practice.handler.FunctionalHandler;

@RunWith(SpringRunner.class)
@WebFluxTest
@Import(value = FunctionalHandler.class)
public class PracticeApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testHelloWorldRouter() {

	}

}
