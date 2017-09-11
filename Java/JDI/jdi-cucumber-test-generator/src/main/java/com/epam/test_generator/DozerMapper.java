package com.epam.test_generator;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

    private static Mapper mapper;

    static {
        List cfg = new ArrayList();
        cfg.add("dozerMapping.xml");
        mapper = new DozerBeanMapper(cfg);
    }

    public static void map(Object source, Object dest) {
        mapper.map(dest, source);
    }

}