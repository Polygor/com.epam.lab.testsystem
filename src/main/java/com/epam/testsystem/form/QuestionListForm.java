package com.epam.testsystem.form;

import com.epam.testsystem.model.Question;
import org.apache.struts.action.ActionForm;

import java.util.ArrayList;
import java.util.List;

public class QuestionListForm extends ActionForm {
    List<Question> questions = new ArrayList<>();

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
