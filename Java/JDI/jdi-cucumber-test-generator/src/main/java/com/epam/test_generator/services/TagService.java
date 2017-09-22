package com.epam.test_generator.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TagService {

	private List<String> tagDAO;

	{
		tagDAO = new ArrayList<>();
		tagDAO.add("tag1");
		tagDAO.add("tag2");
		tagDAO.add("tag3");
	}

	public List<String> getTags() {
		return tagDAO;
	}

}