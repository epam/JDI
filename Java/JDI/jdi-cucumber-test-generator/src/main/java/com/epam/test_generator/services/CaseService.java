package com.epam.test_generator.services;

import com.epam.test_generator.dao.MockDao;
import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.entities.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CaseService{

    @Autowired
    MockDao mockDao;

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private SuitDAO suitDAO;

    @Transactional
    public void addCase(Case cs) { caseDAO.save(cs); }

    @Transactional
    public List<Case> getCasesBySuitId(long suitId){ return suitDAO.findOne(suitId).getCases(); }

    public Case getCase(Long id) {
        return caseDAO.findOne(id);
    }

    @Transactional
    public void removeCase(Long id) {
        caseDAO.delete(id);
    }

    @Transactional
    public void updateCase(Case cs){
        caseDAO.save(cs);
    }

}