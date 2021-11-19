package com.algaworks.algafood.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@Entity
public class Estado {
	
	//NO CADASTRO DE ESTADO IGNORA O ID E VALIDA OS DEMAIS CAMPOS
//	@NotNull(groups = Groups.EstadoId.class)
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id;
	
//	@NotBlank
	@Column(nullable = false)
	private String nome;

}
