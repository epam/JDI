package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Suit;
import org.springframework.stereotype.Component;

@Component
public class SuitTransformer extends AbstractDozerTransformer<Suit, SuitDTO>{

    @Override
    protected Class<Suit> getEntityClass() {
        return Suit.class;
    }

    @Override
    protected Class<SuitDTO> getDTOClass() {
        return SuitDTO.class;
    }

}
