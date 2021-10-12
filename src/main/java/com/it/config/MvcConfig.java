package com.it.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.EncodedResourceResolver;

@Configuration
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {
	
	//for allow access file in location
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
		  .addResourceHandler("/uploads/**")
		  .addResourceLocations("file:D:/project/eclipse/newnormal-api/uploads/")
		  .setCachePeriod(3600)
		  .resourceChain(true)
		  .addResolver(new EncodedResourceResolver());
	}
}