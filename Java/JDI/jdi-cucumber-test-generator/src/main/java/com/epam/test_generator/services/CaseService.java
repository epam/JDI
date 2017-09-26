package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.transformers.CaseTransformer;
import com.epam.test_generator.entities.Suit;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CaseService {

    @Autowired
    private CaseTransformer caseTransformer;

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private SuitDAO suitDAO;

    public CaseDTO addCaseToSuit(CaseDTO cs, long suitId) {
        Case caze = caseTransformer.fromDto(cs);
        suitDAO.getOne(suitId).getCases().add(caze);
        caseDAO.save(caze);

        return cs;
    }

    public List<CaseDTO> getCasesBySuitId(long suitId) {
        List<Case> list = suitDAO.findOne(suitId).getCases();

        return caseTransformer.toDtoList(list);
    }

    public CaseDTO getCase(Long id) {

        return caseTransformer.toDto(caseDAO.getOne(id));
    }

    public void removeCase(long suitId, long caseId) {
        Suit suit = suitDAO.getOne(suitId);
        Case caze = suit.getCaseById(caseId);

        if (caze != null) {
            suit.getCases().remove(caze);
            suitDAO.save(suit);
            caseDAO.delete(caseId);
        }
    }

    public CaseDTO updateCase(long suitId, CaseDTO cs) {
        Suit suit = suitDAO.getOne(suitId);
        Case caze = suit.getCaseById(cs.getId());

        if (caze != null) {
            suit.getCases().remove(caze);
            caze.setSteps(new ArrayList<>());
            suit.getCases().add(caseTransformer.fromDto(cs));
            suitDAO.save(suit);
            cs = caseTransformer.toDto(caze);
        }
        return cs;
    }

    public void removeCases(long suitId, List<Long> caseIds) {
        Suit suit = suitDAO.getOne(suitId);
        suit.getCases().removeIf((c) -> {
            for (Long caseId : caseIds) {
                if (c.getId().equals(caseId)){
                    return true;
                }
            }
            return false;
        });

        suitDAO.save(suit);
    }
}