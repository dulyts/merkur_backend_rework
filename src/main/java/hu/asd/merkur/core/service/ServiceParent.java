package hu.asd.merkur.core.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.querydsl.core.types.EntityPath;

import hu.asd.merkur.core.configuration.MerkurRequestContext.MerkurRequestContextHolder;
import hu.asd.merkur.core.persist.entity.AbstractEntity;
import hu.asd.merkur.core.persist.repository.RepositoryParent;

public class ServiceParent<E extends AbstractEntity, Q extends EntityPath<E>, R extends RepositoryParent<E, Q>> {

	@Autowired
	protected R repository;

	@Autowired
	protected MerkurRequestContextHolder requestContextHolder;

}
