package com.epam.testsystem.service;

import com.epam.testsystem.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BaseService<E extends BaseEntity, T extends CrudRepository<E, Long>> {

    protected T repository;
//Base Service implements basic CRUD operations for all entities
    protected BaseService(T repository) {
        this.repository = repository;
    }

    @Transactional
    public E findById(Long id) {
        return repository.findOne(id);
    }

    @Transactional
    public List<E> findAll() {
        return (List<E>) repository.findAll();
    }

    @Transactional
    public void delete(Long id) {
        repository.delete(id);
    }

    @Transactional
    public E save(E e) {
        return repository.save(e);
    }
}
