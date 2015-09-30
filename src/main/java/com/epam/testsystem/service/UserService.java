package com.epam.testsystem.service;

import com.epam.testsystem.model.User;
import com.epam.testsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User, UserRepository> {

    @Autowired
    public UserService(UserRepository repository) {
        super(repository);
    }

    public List<User> findByRoleName(String roleName) {
        return repository.findByRole_Name(roleName);
    }
}
