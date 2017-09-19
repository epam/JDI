package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.AutoCompleteDAO;
import com.epam.test_generator.dto.AutoCompleteDTO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.entities.AutoComplete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AutoCompleteService {

    @Autowired
    private DozerMapper dozerMapper;

    @Autowired
    private AutoCompleteDAO autoCompleteDAO;

    public List<AutoCompleteDTO> getAutoCompleteList() {
        List<AutoCompleteDTO> autoCompleteDTOList = new ArrayList<>();

        for(AutoComplete autoComplete: autoCompleteDAO.findAll()){
            AutoCompleteDTO autoCompleteDTO = new AutoCompleteDTO();

            dozerMapper.map(autoComplete, autoCompleteDTO);
            autoCompleteDTOList.add(autoCompleteDTO);
        }

        return autoCompleteDTOList;
    }

    public AutoCompleteDTO getAutoComplete(long id) {
        AutoCompleteDTO autoCompleteDTO = new AutoCompleteDTO();

        dozerMapper.map(autoCompleteDAO.getOne(id), autoCompleteDTO);

        return autoCompleteDTO;
    }

    public AutoCompleteDTO addAutoComplete (AutoCompleteDTO autoCompleteDTO) {
        AutoComplete autoComplete = new AutoComplete();

        dozerMapper.map(autoCompleteDTO, autoComplete);
        autoComplete = autoCompleteDAO.save(autoComplete);
        dozerMapper.map(autoComplete, autoCompleteDTO);

        return autoCompleteDTO;
    }

    public void removeAutoComplete (long id) {
        autoCompleteDAO.delete(id);
    }
}
