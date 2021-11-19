package com.algaworks.algafood.core.squiggly;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bohnman.squiggly.Squiggly;
import com.github.bohnman.squiggly.web.RequestSquigglyContextProvider;
import com.github.bohnman.squiggly.web.SquigglyRequestFilter;

//13.3. Limitando os campos retornados pela API com Squiggly
//Nota sobre o Squiggly
//Atualmente o Squiggly não está funcionando bem quando trabalhamos com o Spring HATEOAS 1.0. Como dito na aula, estes problemas podem acontecer em projetos pequenos, mas podemos criar issues e acompanhar o projeto do Squiggly no Github, para vermos quando e se terá atualização para corrigir este problema.
//
//Para responses JSON padrões, sem HATEOAS, o filtro funciona corretamente.
//
//Iremos atualizar aqui, caso haja alguma atualização que corrija o problema.

//FACILITA O RETORNO PERSONALIZADO 
//BASTA PASSAR NA URL /pedidos?campos=codigo,valorTotal

@Configuration
public class SquigglyConfig {

	@Bean
	public FilterRegistrationBean<SquigglyRequestFilter> squigglyRequestFilter(ObjectMapper objectMapper) {
		Squiggly.init(objectMapper, new RequestSquigglyContextProvider("campos", null));
		
		//SERVE PARA LIMITAR O USO DO Squiggly APENAS EM PEDIDOS E RESTAURANTE
		//CASO QUEIRA USAR NO PROJETO GERAL, BASTA TIRAR ESTA CONFIGURAÇÃO
		var urlPatterns = Arrays.asList("/pedidos/*", "/restaurantes/*");
		
		var filterRegistration = new FilterRegistrationBean<SquigglyRequestFilter>();
		filterRegistration.setFilter(new SquigglyRequestFilter());
		filterRegistration.setOrder(1);
		filterRegistration.setUrlPatterns(urlPatterns);
		
		return filterRegistration;
	}
	
}