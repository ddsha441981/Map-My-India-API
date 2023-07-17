package com.cwc.mapmyindia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MapMyIndiaApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapMyIndiaApiApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(byteArrayHttpMessageConverter());
	    return restTemplate;
	}
	
	
	/*
	 * private ByteArrayHttpMessageConverter getByteArrayMessageConverter() {
	 * ByteArrayHttpMessageConverter converter = new
	 * ByteArrayHttpMessageConverter();
	 * converter.setSupportedMediaTypes(Arrays.asList(MediaType.IMAGE_JPEG,
	 * MediaType.IMAGE_GIF, MediaType.IMAGE_PNG)); return converter; }
	 */
	
	private ByteArrayHttpMessageConverter byteArrayHttpMessageConverter() {
	    ByteArrayHttpMessageConverter arrayHttpMessageConverter = new ByteArrayHttpMessageConverter();
	    arrayHttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
	    return arrayHttpMessageConverter;
	}

	private List<MediaType> getSupportedMediaTypes() {
	    List<MediaType> list = new ArrayList<MediaType>();
	    list.add(MediaType.IMAGE_JPEG);
	    list.add(MediaType.IMAGE_PNG);
	    list.add(MediaType.APPLICATION_OCTET_STREAM);
	    return list;
	}

}
