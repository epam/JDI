package com.epam.test_generator.dao.interfaces;

import java.util.List;

public interface EntitiesDAO<T> {

    T addTestEntity(T ts);

    List<T> getAllTestEntities();

    T getEntity(Long id);

    void editTestEntity(T ts);

    void removeTestEntity(Long id);
}
