package it.unibo.games;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


@Configuration
//@EnableWebMvc //not need because Spring boot takes care of this
public class WebConfig implements WebMvcConfigurer{
	
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/webjars/**")
          //.addResourceLocations("classpath:/META-INF/resources/webjars/")
          .addResourceLocations("/webjars/")
          .resourceChain(false);
    }
}