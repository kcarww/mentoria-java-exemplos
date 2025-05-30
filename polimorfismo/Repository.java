package com.sollaris.admin.catalogo.infrastructure;

public interface Repository<T> {
    T save(T entity);
    T update(T entity);
    T findById(String id);
    void deleteById(String id);
}

