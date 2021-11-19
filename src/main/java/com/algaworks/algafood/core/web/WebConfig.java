package com.algaworks.algafood.core.web;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//CONFIGURANDO O CORS NA API

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//ACEITA QUALQUER ORIGEM ACESSAR
		registry.addMapping("/**")
			//QUAIS OS METODOS ACEITOS(PUT, GET, POST)
			//COM * ACEITA TODOS
			.allowedMethods("*");
			//APENAS IRÁ ACEITAR A ORIGEM INFORMADA
//			.allowedOrigins("*")
//			.maxAge(30);
	}
	
	//CONFIGURAÇÃO PARA UTILIZAR CACHE COM Shallow ETags
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
	
}
