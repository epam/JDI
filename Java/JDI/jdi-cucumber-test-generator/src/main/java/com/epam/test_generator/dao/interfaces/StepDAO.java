package com.epam.test_generator.dao.interfaces;

import com.epam.test_generator.entities.Step;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepDAO extends JpaRepository<Step,Long> {
}
