package com.sollaris.admin.catalogo.infrastructure;

import java.util.List;

public interface IRepository<T> {
    T save(T entity);
    T findById(String id);
    void delete(String id);
    List<T> findAll();
    void update(T entity);
}
