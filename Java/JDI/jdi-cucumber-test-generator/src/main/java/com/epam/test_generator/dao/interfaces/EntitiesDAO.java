package com.epam.test_generator.dao.interfaces;

import java.util.List;

public interface EntitiesDAO<T> {

    T addEntity(T ts);

    List<T> getAllEntities();

    T getEntity(Long id);

    void editEntity(T ts);

    void removeEntity(Long id);
}
