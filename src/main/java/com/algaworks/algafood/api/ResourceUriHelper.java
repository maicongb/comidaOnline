package com.algaworks.algafood.api;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.experimental.UtilityClass;

//ADICIONA A URI DE PESQUISA NO HEADER APÓS CADASTRAR RECURSO
@UtilityClass
public class ResourceUriHelper {
	
	public static void addUriInResponseHeader(Object resourceId) {
	
		//EXEMPLO http://localhost:8080/cidades/1
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
					.path("/{id}")
					.buildAndExpand(resourceId).toUri();
		
		HttpServletResponse response = ((ServletRequestAttributes) 
				RequestContextHolder.getRequestAttributes()).getResponse();
		
		//ADICIONAR NO HEADER http://localhost:8080/cidades/1
		response.setHeader(HttpHeaders.LOCATION, uri.toString());
											
	}

}
