package com.wow.api.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	 @Bean(name="JSONView")
	 public MappingJackson2JsonView JSONView(){
	        return new MappingJackson2JsonView();
	    }
	/*
	 * private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
	 * "classpath:/static/", "classpath:/public/", "classpath:/",
	 * "classpath:/resources/", "classpath:/META-INF/resources/",
	 * "classpath:/META-INF/resources/webjars/" };
	 * 
	 * @Override //configure 메소드를 통해 어떤 요청에 대해서는 로그인을 요구하고, 어떤 요청에 대해서 로그인을 요구하지 않을지
	 * 설정한다. public void addViewControllers(ViewControllerRegistry registry) { // /에
	 * 해당하는 url mapping을 /common/test로 forward한다. registry.addViewController( "/"
	 * ).setViewName( "forward:/index" ); // 우선순위를 가장 높게 잡는다.
	 * registry.setOrder(Ordered.HIGHEST_PRECEDENCE); }
	 * 
	 * 
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * registry.addResourceHandler("/**").addResourceLocations(
	 * CLASSPATH_RESOURCE_LOCATIONS); }
	 */
}
