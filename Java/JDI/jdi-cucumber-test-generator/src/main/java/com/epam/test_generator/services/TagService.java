package com.epam.test_generator.services;

import java.util.ArrayList;
import java.util.List;

import com.epam.test_generator.dao.interfaces.TagDAO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dto.TagDTO;
import com.epam.test_generator.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TagService {

	@Autowired
	private TagDAO tagDAO;

	@Autowired
	private DozerMapper mapper;

	public TagDTO save(TagDTO tagDTO){
		Tag tag = new Tag();
		mapper.map(tagDTO, tag);
		tag = tagDAO.save(tag);
		mapper.map(tag, tagDTO);
		return tagDTO;
	}

	public List<TagDTO> getTags() {
		List<Tag> tags = tagDAO.findAll();
		List<TagDTO> result = new ArrayList<>();

		for(Tag tag : tags){
			TagDTO tagDTO = new TagDTO();
			mapper.map(tag, tagDTO);
			result.add(tagDTO);
		}

		return result;
	}

}