package com.epam.mock.persistence;

import com.epam.mock.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}