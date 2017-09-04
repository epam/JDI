package com.epam.test_generator.services.interfaces;

import java.util.List;

public interface EntitiesService<T> {

    void addEntity(T ts);

    List<T> getAllEntities();

    T getEntity(Long id);

    void removeEntity(Long id);

}
