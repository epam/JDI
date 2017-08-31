package com.epam.test_generator.services.interfaces;

import java.util.List;

public interface EntitiesService<T> {

    void addTestEntity(T ts);

    List<T> getAllTestEntities();

    T getEntity(Long id);

    void removeTestEntity(Long id);

}
