package com.epam.test_generator.dao.interfaces;

import com.epam.test_generator.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDAO extends JpaRepository<Tag,Long> {

}