package com.epam.test_generator;

import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.entities.Step;
import com.epam.test_generator.entities.StepType;
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

    public static void main(String[] args) {
        Step src = new Step();
        StepDTO dest = new StepDTO();

        src.setId(1L);
        src.setRowNumber(1);
        src.setDescription("description of step");
        src.setType(StepType.BUT);



        System.out.println("before: " + dest);
        DozerMapper.mapper.map(src, dest);
        System.out.println("after: " + dest);
    }


}