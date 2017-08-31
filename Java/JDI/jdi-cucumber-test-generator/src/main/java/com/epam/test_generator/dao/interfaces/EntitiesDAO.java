package com.epam.test_generator.dao.interfaces;

import java.util.List;

public interface EntitiesDAO<T> {

    void addTestEntity(T ts);

    List<T> getAllTestEntities();
    T getEntity(Long id);

    void removeTestEntity(Long id);
}
