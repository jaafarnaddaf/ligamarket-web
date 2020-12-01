package com.livelightlabs.livelightgames.ligamarket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.livelightlabs.livelightgames.ligamarket.entity.BaseEntity;
import com.livelightlabs.livelightgames.ligamarket.repository.BaseEntityRepository;

public class BaseEntityService<T extends BaseEntity> {

	@Autowired
	private BaseEntityRepository<T> repository;

	public long count() {
		return repository.count();
	}

	public void delete(T entity) {
		repository.delete(entity);
	}

	public void deleteAll() {
		repository.deleteAll();
	}

	public <S extends T> void deleteAll(Iterable<S> entities) {
		repository.deleteAll(entities);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	public List<T> findAll() {
		return repository.findAll();
	}

	public Page<T> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	public List<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	public List<T> findAllById(Iterable<Long> ids) {
		return repository.findAllById(ids);
	}

	public Optional<T> findById(Long id) {
		return repository.findById(id);
	}

	public <S extends T> S save(S entity) {
		return repository.save(entity);
	}

	public <S extends T> List<S> saveAll(Iterable<S> entities) {
		return repository.saveAll(entities);
	}
}