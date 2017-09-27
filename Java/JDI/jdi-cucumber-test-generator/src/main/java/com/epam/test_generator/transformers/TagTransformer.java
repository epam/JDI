package com.epam.test_generator.transformers;

import com.epam.test_generator.dto.TagDTO;
import com.epam.test_generator.entities.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagTransformer extends AbstractDozerTransformer<Tag, TagDTO> {

    @Override
    protected Class<Tag> getEntityClass() {
        return Tag.class;
    }

    @Override
    protected Class<TagDTO> getDTOClass() {
        return TagDTO.class;
    }

}
