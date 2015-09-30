package com.epam.testsystem.service;

import com.epam.testsystem.model.Question;
import com.epam.testsystem.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionService extends BaseService<Question, QuestionRepository> {

    @Autowired
    protected QuestionService(QuestionRepository repository) {
        super(repository);
    }

}
