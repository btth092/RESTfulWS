/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hegco.restfulws.utm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author hegco
 */
@Configuration
@EnableWebMvc
@ComponentScan(
		basePackages = {
				"com.hegco.restfulws.utm.rest",
				"com.hegco.restfulws.utm.rest.exception"}
				)
public class RestServletContextConfig extends WebMvcConfigurerAdapter{
    @Inject 
    ObjectMapper objectMapper;
    @Inject 
    Marshaller marshaller;
    @Inject 
    Unmarshaller unmarshaller;

    
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		
		converters.add(createXMLMessageConverter());
	
		converters.add(createJSONMessageConverter());
	}
	
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorPathExtension(false).favorParameter(false);
        configurer.ignoreAcceptHeader(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
    }
    @Bean(name="multipartResolver")
	public MultipartResolver multipartResolver() {
            
		return new StandardServletMultipartResolver();
	}
    
   
	private MappingJackson2HttpMessageConverter createJSONMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setSupportedMediaTypes(
				Arrays.asList(new MediaType("application", "json"), new MediaType("text", "json")));
		jsonConverter.setObjectMapper(this.objectMapper);
		return jsonConverter;
	}
	

	private MarshallingHttpMessageConverter createXMLMessageConverter() {
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
		xmlConverter.setSupportedMediaTypes(
				Arrays.asList(new MediaType("application", "xml"), new MediaType("text", "xml")));
		xmlConverter.setMarshaller(marshaller);
		xmlConverter.setUnmarshaller(unmarshaller);
		return xmlConverter;
	}
}