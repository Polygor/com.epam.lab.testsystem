package com.epam.testsystem.service;

import com.epam.testsystem.model.TestCase;
import com.epam.testsystem.repository.TestCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestCaseService extends BaseService<TestCase, TestCaseRepository> {

    @Autowired
    public TestCaseService(TestCaseRepository repository) {
        super(repository);
    }

        @Transactional
    public List<TestCase> findByUserId(Long id) {
        return repository.findByUser_Id(id);
    }
}
