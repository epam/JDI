package com.epam.test_generator;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private Mapper mapper;

    public DozerMapper(){
        List cfg = new ArrayList();
        cfg.add("dozerMapping.xml");
        mapper = new DozerBeanMapper(cfg);
    }

    public void map(Object source, Object dest) {
        mapper.map(dest, source);
    }
}