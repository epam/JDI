package com.epam.test_generator.services;

import com.epam.test_generator.DozerMapper;
import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Suit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class SuitService {

    @Autowired
    private SuitDAO suitDAO;

    @Autowired
    private DozerMapper mapper;

    public List<SuitDTO> getSuits() {
        List<SuitDTO> suitDTOlist = new ArrayList<>();
        for(Suit suit : suitDAO.findAll()){
            SuitDTO suitDTO = new SuitDTO();
            mapper.map(suit, suitDTO);
            suitDTOlist.add(suitDTO);
        }

        return suitDTOlist;
    }

    public SuitDTO getSuit(long id) {
        SuitDTO suitDTO = new SuitDTO();
        mapper.map(suitDAO.findOne(id), suitDTO);

        return suitDTO;
    }

    public SuitDTO updateSuit(SuitDTO suitDTO) {
        Suit suit = new Suit();
        mapper.map(suitDTO, suit);
        mapper.map(suitDAO.save(suit), suitDTO);

        return suitDTO;
    }

    public void removeSuit(long id) {
        suitDAO.delete(id);
    }

    public SuitDTO addSuit(SuitDTO suitDTO) {
        Suit suit = new Suit();
        mapper.map(suitDTO,suit);
        suit = suitDAO.save(suit);
        mapper.map(suit, suitDTO);

        return suitDTO;
    }

    public SuitDTO getSuitByName(String name) {
        SuitDTO suitDTO = new SuitDTO();
        mapper.map(suitDAO.getSuitByName(name),suitDTO);

        return suitDTO;
    }
}