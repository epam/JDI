package com.epam.test_generator.services;

import java.util.List;
import java.util.stream.Collectors;

import com.epam.test_generator.dao.interfaces.TagDAO;
import com.epam.test_generator.dto.TagDTO;
import com.epam.test_generator.entities.Tag;
import com.epam.test_generator.transformers.TagTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TagService {

	@Autowired
	private TagDAO tagDAO;

	@Autowired
	private TagTransformer tagTransformer;

	public TagDTO save(TagDTO tagDTO){
		Tag tag = tagDAO.save(tagTransformer.fromDto(tagDTO));

		return tagTransformer.toDto(tag);
	}

	public List<TagDTO> getTags() {
		return tagDAO.findAll().stream()
				.map((t)-> tagTransformer.toDto(t))
				.collect(Collectors.toList());
	}

}