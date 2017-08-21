package com.epam.mock.service;

import com.epam.mock.model.User;
import com.epam.mock.persistence.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    private static List<User> userStartList = new ArrayList<>();

    static {
        userStartList.add(new User("1", "Leo", "admin"));
        userStartList.add(new User("2", "Mickey", "client"));
        userStartList.add(new User("3", "Raph", "client"));
    }

    @PostConstruct
    public void init() {
        repository.save(userStartList);
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();

        repository.findAll().forEach(users :: add);

        return users;
    }

    public User getUser(String id) {
        return repository.findOne(id);
    }

    public void addUser(User user) {
        repository.save(user);
    }

    public void updateUser(String id, User user) {
        if (id.equals(user.getId())) {
            repository.save(user);
        }
    }

    public void updateUserField(String id, Map<String, String> requestParams) {
        User user = repository.findOne(id);

        if (requestParams.get("name") != null) {
            user.setName(requestParams.get("name"));
        }

        if (requestParams.get("role") != null) {
            user.setRole(requestParams.get("role"));
        }

        repository.save(user);
    }

    public void deleteUser(String id) {
        repository.delete(id);
    }

    public void deleteUsers() {
        repository.deleteAll();
    }
}