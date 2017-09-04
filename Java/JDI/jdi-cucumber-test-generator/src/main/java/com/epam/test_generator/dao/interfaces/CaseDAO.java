package com.epam.test_generator.dao.interfaces;

import com.epam.test_generator.entities.Case;

import java.util.List;

public interface CaseDAO {

    void addCase(Case cs);

    List<Case> getAllCases();

    Case getCase(long id);

    void removeCase(long id);

    List<Case> getCasesBySuitId(long suitId);

    void updateCase(Case cs);
}
