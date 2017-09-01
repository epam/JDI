package com.epam.test_generator.dao.interfaces;

import java.util.List;

public interface EntitiesDAO<T> {

    void addEntity(T ts);

    List<T> getAllEntities();
    T getEntity(Long id);

    void removeEntity(Long id);
}
