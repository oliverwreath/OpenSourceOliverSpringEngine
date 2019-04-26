package com.oli.Services;

import com.oli.Services.Interfaces.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Oliver
 */
@Slf4j
@Service
public abstract class ServiceAbstract<T, ID> implements ServiceInterface<T, ID> {
    abstract PagingAndSortingRepository<T, ID> getRepository();

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();
        getRepository().findAll().forEach(entities::add);
        return entities;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        return getRepository().findAll(pageable);
    }

    @Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public T saveOrReplace(T entity) {
        return getRepository().save(entity);
    }

    @Override
    public void deleteById(ID id) {
        getRepository().deleteById(id);
    }

    @Override
    public void delete(T t) {
        getRepository().delete(t);
    }

    @Override
    public long count() {
        return getRepository().count();
    }

    @Override
    public boolean existsById(ID id) {
        return getRepository().existsById(id);
    }

    public abstract long countByCreatedBy(String user);

    @Override
    public Iterable<T> saveAll(Iterable<T> var1) {
        return getRepository().saveAll(var1);
    }

    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        getRepository().deleteAll(entities);
    }

    @Override
    public void deleteAll() {
        getRepository().deleteAll();
    }
}
