package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.entities.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CaseService{

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private SuitDAO suitDAO;

    public Case addCaseToSuit(Case cs, long suitId) {
        cs.setSuit(suitDAO.getOne(suitId));
        return caseDAO.save(cs);
    }

    public List<Case> getCasesBySuitId(long suitId){ return suitDAO.findOne(suitId).getCases(); }

    public Case getCase(Long id) {
        return caseDAO.findOne(id);
    }

    public void removeCase(Long id) { caseDAO.delete(id); }

    public Case updateCase(Case cs){
        cs.setSuit(caseDAO.getOne(cs.getId()).getSuit());
        return caseDAO.save(cs);
    }

}