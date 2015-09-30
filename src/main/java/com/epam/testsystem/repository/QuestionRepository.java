package com.epam.testsystem.repository;

import com.epam.testsystem.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {
    Question findByTitle(String title);
}
