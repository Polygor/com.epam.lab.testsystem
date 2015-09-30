package com.epam.testsystem.repository;

import com.epam.testsystem.model.Test;
import org.springframework.data.repository.CrudRepository;

public interface TestRepository extends CrudRepository<Test, Long> {
        
}
