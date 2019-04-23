package ai.clova.practice.config;

import java.io.IOException;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

@Configuration
public class YamlConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer yamlProperties() throws IOException {
		PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
		YamlPropertiesFactoryBean yamlFactoryBean = new YamlPropertiesFactoryBean();

		ResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = patternResolver.getResources("classpath*:**/*.yml");

		yamlFactoryBean.setResources(resources);
		configurer.setProperties(yamlFactoryBean.getObject());
		return configurer;
	}
}
