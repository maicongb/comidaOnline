package com.algaworks.algafood.infrastructure.repository;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.algaworks.algafood.domain.repository.CustomJpaRepository;

public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID> 
		implements CustomJpaRepository<T, ID> {
	
	private EntityManager manager;

	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, 
			EntityManager entityManager) {
		super(entityInformation, entityManager);

		this.manager = entityManager;
	}


	//ESSE METODO É GENERICO, BUSCARÁ O PRIMEIRO REGISTRO DE QUALQUER ENTIDADE QUE EXTENDER O CustomJpaRepository
	//getDomainClass().getName() -> PAGA O NOME DA ENTIDADE
	
	@Override
	public Optional<T> buscarPrimeiro() {
		
		var jpql = "from " + getDomainClass().getName();
		
		T entity = manager.createQuery(jpql, getDomainClass())
				.setMaxResults(1)
				.getSingleResult();
		
		return Optional.ofNullable(entity);
	}


	@Override
	public Optional<T> buscarUltimo(Long id) {
		CriteriaBuilder cb = manager.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(getDomainClass());	
		Root<T> root = query.from(getDomainClass());
		
		
		Subquery<T> subQuery = query.subquery(getDomainClass());
		Root<T> rootSubQuery = subQuery.from(getDomainClass());
		subQuery.select(rootSubQuery.get("id"));
		subQuery.where(cb.equal(rootSubQuery.get("id"), id));
		
		query.where(cb.equal(root.get("id"), subQuery));
		
		T entity = manager.createQuery(query).getSingleResult();
	
		return Optional.ofNullable(entity);
		

	}

	@Override
	public void detach(T entity) {
		manager.detach(entity);
		
	}


}
