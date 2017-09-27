package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.DozerMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDozerTransformer<E, D> {

    @Autowired
    private DozerMapper dozerMapper;

    protected abstract Class<E> getEntityClass();
    protected abstract Class<D> getDTOClass();

    @SuppressWarnings("unchecked")
    public E fromDto(D dto){
        return (E) dozerMapper.map(dto, getEntityClass());
    }

    @SuppressWarnings("unchecked")
    public D toDto(E entity){
        return (D) dozerMapper.map(entity, getDTOClass());
    }

    public List<E> fromDtoList(List<D> dtoList){
        return dtoList.stream().map(this::fromDto).collect(Collectors.toList());
    }

    public List<D> toDtoList(List<E> entityList){
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

}
