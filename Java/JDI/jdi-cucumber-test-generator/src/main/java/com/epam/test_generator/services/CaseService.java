package com.epam.test_generator.services;

import com.epam.test_generator.DozerMapper;
import com.epam.test_generator.dao.interfaces.CaseDAO;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.entities.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CaseService{

    @Autowired
    private CaseDAO caseDAO;

    @Autowired
    private SuitDAO suitDAO;

    @Autowired
    private DozerMapper mapper;

    public CaseDTO addCaseToSuit(CaseDTO caseDTO, long suitId) {
        Case caze = new Case();
        mapper.map(caseDTO, caze);
        caze.setSuit(suitDAO.getOne(suitId));
        mapper.map(caseDAO.save(caze),caseDTO);

        return caseDTO;
    }

    public List<CaseDTO> getCasesBySuitId(long suitId){
        List<CaseDTO> caseDTOlist = new ArrayList<>();
        for(Case caze : suitDAO.findOne(suitId).getCases()) {
            CaseDTO caseDTO = new CaseDTO();
            mapper.map(caze,caseDTO);
            caseDTOlist.add(caseDTO);
        }

        return caseDTOlist;
    }

    public CaseDTO getCase(Long id) {
        CaseDTO caseDTO = new CaseDTO();
        mapper.map(caseDAO.findOne(id), caseDTO);

        return caseDTO;
    }

    public void removeCase(Long id) { caseDAO.delete(id); }

    public CaseDTO updateCase(CaseDTO caseDTO){
        Case caze = new Case();
        mapper.map(caseDTO, caze);
        caze.setSuit(caseDAO.getOne(caze.getId()).getSuit());
        mapper.map(caseDAO.save(caze),caseDTO);

        return caseDTO;
    }
}