package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Suit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SuitTransformer {

    @Autowired
    private DozerMapper dozerMapper;

    public Suit fromDto(SuitDTO suitDTO){
        Suit suit = new Suit();
        dozerMapper.map(suitDTO, suit);
        return suit;
    }

    public SuitDTO toDto(Suit suit){
        SuitDTO suitDTO = new SuitDTO();
        dozerMapper.map(suit, suitDTO);
        return suitDTO;
    }
}
