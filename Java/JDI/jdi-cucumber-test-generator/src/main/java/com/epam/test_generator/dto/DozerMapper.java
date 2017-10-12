package com.epam.test_generator.dto;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DozerMapper<E> {

    private Mapper mapper;

    public DozerMapper() {
        List cfg = new ArrayList();

        cfg.add("dozerMapping.xml");
        mapper = new DozerBeanMapper(cfg);
    }

    public void map(Object source, Object dest) {
        mapper.map(source, dest);
    }

    public E map(Object source, Class<E> dest) {
        return mapper.map(source, dest);
    }

}