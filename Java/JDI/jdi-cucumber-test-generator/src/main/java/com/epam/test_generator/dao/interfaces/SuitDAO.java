package com.epam.test_generator.dao.interfaces;

import com.epam.test_generator.entities.Suit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SuitDAO extends JpaRepository<Suit,Long> {

}