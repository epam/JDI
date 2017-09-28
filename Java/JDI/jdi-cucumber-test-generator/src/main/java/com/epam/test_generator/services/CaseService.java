package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dao.interfaces.TagDAO;
import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.transformers.CaseTransformer;
import com.epam.test_generator.entities.Suit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class CaseService {

    @Autowired
    private CaseTransformer caseTransformer;

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private SuitDAO suitDAO;

    @Autowired
    private TagDAO tagDAO;

    public CaseDTO addCaseToSuit(CaseDTO cs, long suitId) {
        Suit suit = suitDAO.getOne(suitId);
        Case caze = caseTransformer.fromDto(cs);

        mergeTags(caze);

        suit.getCases().add(caze);
        suitDAO.save(suit);

        return caseTransformer.toDto(caze);
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

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        cs.setUpdateDate(formatter.format(Calendar.getInstance().getTime()));

        if (caze != null) {
            suit.getCases().remove(caze);

            caze.setSteps(new ArrayList<>());
            caze.setTags(new HashSet<>());

            caze = caseTransformer.fromDto(cs);

            mergeTags(caze);

            suit.getCases().add(caze);
            suitDAO.save(suit);
            cs = caseTransformer.toDto(caze);
        }
        return cs;
    }

    public void removeCases(long suitId, List<Long> caseIds) {
        Suit suit = suitDAO.getOne(suitId);
        suit.getCases()
                .removeIf(caze -> caseIds.stream()
                        .anyMatch(id -> id.equals(caze.getId())));

        suitDAO.save(suit);
    }

    private void mergeTags(Case caze){
        if(caze.getTags() !=null){
            caze.setTags(caze.getTags().stream()
                    .map(tag -> tagDAO.findOne(Example.of(tag))==null ? tag : tagDAO.findOne(Example.of(tag)))
                    .collect(Collectors.toSet()));
        }
    }
}