package com.epam.test_generator.dao;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.entities.Case;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CaseDAOImpl implements CaseDAO {

    @Override
    public void addCase(Case cs) { }

    @Override
    public List<Case> getAllCases() {
        return null;
    }

    @Override
    public Case getCase(long id) {
        return null;
    }

    @Override
    public void removeCase(long id) { }

    @Override
    public List<Case> getCasesBySuitId(long suitId){
        return null;
    }
}
