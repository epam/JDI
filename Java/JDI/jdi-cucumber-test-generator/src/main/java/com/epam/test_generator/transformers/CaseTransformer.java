package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.entities.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CaseTransformer {

    @Autowired
    private DozerMapper dozerMapper;

    public Case fromDto(CaseDTO caseDTO){
        Case caze = new Case();
        dozerMapper.map(caseDTO, caze);
        return caze;
    }

    public CaseDTO toDto(Case caze){
        CaseDTO caseDTO = new CaseDTO();
        dozerMapper.map(caze, caseDTO);
        return caseDTO;
    }

    public List<Case> fromDtoList(List<CaseDTO> caseDTOList){
        List<Case> caseList = new ArrayList<>();
        dozerMapper.map(caseDTOList, caseList);
        return caseList;
    }

    public List<CaseDTO> toDtoList(List<Case> caseList){
        List<CaseDTO> caseDTOList = new ArrayList<>();
        dozerMapper.map(caseList, caseDTOList);
        return caseDTOList;
    }
}
