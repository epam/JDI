package com.epam.test_generator.dao;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.TestCase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestCaseDAOImpl implements EntitiesDAO<TestCase>{

    @Override
    public void addTestEntity(TestCase ts) {

    }

    @Override
    public List<TestCase> getAllTestEntities() {
        return null;
    }

    @Override
    public TestCase getEntity(Long id) {
        return null;
    }

    @Override
    public void removeTestEntity(Long id) {

    }
}
