package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Transactional
@Service
public class CaseService {

    @Autowired
    private DozerMapper mapper;

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private SuitDAO suitDAO;

    public CaseDTO addCaseToSuit(CaseDTO cs, long suitId) {
        Case caze = new Case();

        mapper.map(cs, caze);
        suitDAO.getOne(suitId).getCases().add(caze);
        mapper.map(caseDAO.save(caze), cs);

        return cs;
    }

    public List<CaseDTO> getCasesBySuitId(long suitId) {
        List<CaseDTO> listDTO = new ArrayList<>();
        List<Case> list = suitDAO.findOne(suitId).getCases();

        mapper.map(list, listDTO);

        return listDTO;
    }

    public CaseDTO getCase(Long id) {
        CaseDTO dto = new CaseDTO();

        mapper.map(caseDAO.getOne(id), dto);

        return dto;
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
//        System.out.println(caze.getDescription() + " " + caze.getUpdateDate());

        if (caze != null) {
            suit.getCases().remove(caze);
            caze.setSteps(new ArrayList<>());
            mapper.map(cs, caze);
            suit.getCases().add(caze);
            suitDAO.save(suit);
            mapper.map(caze, cs);
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