package com.algaworks.algafood.core.validation.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.api.model.input.ItemPedidoInput;
import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;

//ESTA CONFIGURAÇÃO SERVE PARA COLOCAR O MODELMAPPER DENTRO DO SPRING
//DESTA FORMA PODEREMOS FAZER A INJEÇÃO @AUTOWIRED

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		//SERVE PARA ALTERAR O NOME DE SAUDA DE TAXAFRETE PARA PRECOFRETE
//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//			.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
		
		//SERVE PARA USAR O NOME ESTADO NA RESPOSTA DE RESTAURANTE
		//QUE SE UTILIZA DA CLASSE CidadeResumoModel
		
		var enderecoToEnderecoModelTypeMap = modelMapper.createTypeMap(
				Endereco.class, EnderecoModel.class);
		
		enderecoToEnderecoModelTypeMap.<String>addMapping(
				enderecoSrc -> enderecoSrc.getCidade().getEstado().getNome(),
				(enderecoModelDest, value) -> enderecoModelDest.getCidade().setEstado(value));
		
		//NA HORA DE FAZER O INSERT, O JPA IRA IGNORAR O ID DO ITEMPEDIDO, 
		//POIS ELE É AUTOINCREMENTO
		modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
	    					.addMappings(mapper -> mapper.skip(ItemPedido::setId));  
		
		return modelMapper;
	}
	
}
