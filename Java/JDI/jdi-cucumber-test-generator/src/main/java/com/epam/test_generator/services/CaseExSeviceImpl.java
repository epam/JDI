package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.CaseType;
import com.epam.test_generator.services.interfaces.CaseExService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CaseExSeviceImpl  implements CaseExService {

    private CaseDAO caseDAO;

    @Autowired
    public void setCaseDAO(CaseDAO caseDAO){
        this.caseDAO=caseDAO;
    }

    @Override
    public void addCase(CaseType type, String feature, String scenario) {
        Case caze= new Case(null,feature, scenario,null);
        caze.setType(type);
        caseDAO.save(caze);
    }

    @Override
    public List<Case> getAllCases() {
        return caseDAO.findAll();
    }
}
