package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.entities.Case;
import org.springframework.stereotype.Component;

@Component
public class CaseTransformer extends AbstractDozerTransformer<Case, CaseDTO>{

    @Override
    protected Class<Case> getEntityClass() {
        return Case.class;
    }

    @Override
    protected Class<CaseDTO> getDTOClass() {
        return CaseDTO.class;
    }

}
