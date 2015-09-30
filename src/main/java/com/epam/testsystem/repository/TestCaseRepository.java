package com.epam.testsystem.repository;

import com.epam.testsystem.model.TestCase;
import com.epam.testsystem.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TestCaseRepository extends CrudRepository<TestCase, Long> {

    List<TestCase> findByUser_Id(Long userId);
}
