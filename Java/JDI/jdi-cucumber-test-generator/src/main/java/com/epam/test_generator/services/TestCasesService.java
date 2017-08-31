package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.TestCase;
import com.epam.test_generator.services.interfaces.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestCasesService implements EntitiesService<TestCase>{

    @Autowired
    private EntitiesDAO<TestCase> testCaseDAO;

    @Override
    @Transactional
    public void addTestEntity(TestCase ts) {
        testCaseDAO.addTestEntity(ts);

    }

    @Override
    @Transactional
    public List<TestCase> getAllTestEntities() {
        return testCaseDAO.getAllTestEntities();
    }

    @Override
    public TestCase getEntity(Long id) {
        return testCaseDAO.getEntity(id);
    }

    @Override
    @Transactional
    public void removeTestEntity(Long id) {
        testCaseDAO.removeTestEntity(id);
    }
}
