package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.entities.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CasesService{

    @Autowired
    private CaseDAO caseDAO;

    @Transactional
    public void addCase(Case cs) { caseDAO.addCase(cs); }

    @Transactional
    public List<Case> getCasesBySuitId(long suitId){ return caseDAO.getCasesBySuitId(suitId); }
    
    @Transactional
    public List<Case> getAllCases() {
        return caseDAO.getAllCases();
    }

    public Case getCase(Long id) {
        return caseDAO.getCase(id);
    }

    @Transactional
    public void removeCase(Long id) {
        caseDAO.removeCase(id);
    }

    @Transactional
    public void updateCase(Case cs){
      caseDAO.updateCase(cs);
    }
  
}
