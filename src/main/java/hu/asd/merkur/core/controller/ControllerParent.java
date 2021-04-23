package hu.asd.merkur.core.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.querydsl.core.types.EntityPath;

import hu.asd.merkur.core.persist.entity.AbstractEntity;
import hu.asd.merkur.core.persist.repository.RepositoryParent;
import hu.asd.merkur.core.service.ServiceParent;

public abstract class ControllerParent<E extends AbstractEntity, Q extends EntityPath<E>, R extends RepositoryParent<E, Q>, S extends ServiceParent<E, Q, R>> {

	@Autowired
	protected R repository;

	@Autowired
	protected S service;

	@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
	@GetMapping(path = { "/{id}" })
	public E findOne(@PathVariable("id") UUID id) {
		return repository.findById(id).orElseGet(null);
	}

//	@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
//	@GetMapping
////	public Page<E> findAll(@QuerydslPredicate() Predicate predicate, Pageable pageable) {
////		return repository.findAll(predicate, pageable);
//	public Page<E> findAll(Pageable pageable) {
//		return repository.findAll(pageable);
//	}

	@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
	@PutMapping
	public void update(E entity) {
		repository.save(entity);
	}

	@PreAuthorize("hasAnyRole('ROLE_SUPERADMIN')")
	@DeleteMapping(path = { "/{id}" })
	public void delete(@PathVariable("id") UUID id) {
		repository.deleteById(id);
	}

}
