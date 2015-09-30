package com.epam.testsystem.service;

import com.epam.testsystem.form.TestForm;
import com.epam.testsystem.model.Test;
import com.epam.testsystem.model.User;
import com.epam.testsystem.repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.epam.testsystem.util.SpringUtils.getCurrentlyAuthenticatedUser;

@Service
public class TestService extends BaseService<Test, TestRepository> {

    @Autowired
    public TestService(TestRepository repository) {
        super(repository);
    }

    @Transactional
    public Test createOrUpdate(TestForm form) {
        User user = getCurrentlyAuthenticatedUser();
        Long id = form.getId();
        Test test;
        if(id == null || id.equals(0L)) {
            test = new Test();
            test.setCreatedBy(user);
        } else {
            test = repository.findOne(form.getId());
            test.setUpdatedBy(user);
        }
        form.update(test);
        Test saved = repository.save(test);
        return saved;
    }
}
