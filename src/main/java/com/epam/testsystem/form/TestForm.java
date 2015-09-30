package com.epam.testsystem.form;

import com.epam.testsystem.model.Question;
import com.epam.testsystem.model.Test;

import java.time.Duration;
import java.util.List;

public class TestForm extends BaseForm<Test> {

    private Test test;
    private String title;
    private Double passMark;
    private Duration duration;
    private List<QuestionForm> questions = new StrutsArrayList<>(QuestionForm.class);

    public TestForm() {
    }

    public TestForm(Test test) {
        this.test = test;
    }

    public TestForm(List<QuestionForm> questions) {
        this.questions = questions;
    }

    public TestForm(String title, Double passMark, Duration duration, List<QuestionForm> questions) {
        this.title = title;
        this.passMark = passMark;
        this.duration = duration;
        this.questions = questions;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPassMark() {
        return passMark;
    }

    public void setPassMark(Double passMark) {
        this.passMark = passMark;
    }

    public List<QuestionForm> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionForm> questions) {
        this.questions = questions;
    }

    public Long getDuration() {
        if (duration == null){
            return null;
        } else {
            return duration.toMinutes();
        }
    }

    public void setDuration(Long duration) {
        this.duration = Duration.ofMinutes(duration);
    }

    public void map(Test test) {
        if (test == null)
            return;

        this.id = test.getId();
        this.title = test.getTitle();
        this.passMark = test.getPassMark();
        this.duration = test.getDuration();
        for (Question question : test.getQuestions()) {
            QuestionForm questionForm = new QuestionForm();
            questionForm.map(question);
            this.questions.add(questionForm);
        }
    }

    public void update(Test test) {
        test.setTitle(title);
        test.setPassMark(passMark);
        test.setDuration(duration);
        for (QuestionForm questionForm : questions) {
            Long questionFormId = questionForm.getId();
            Question question = test.getQuestionById(questionFormId);
            if (question != null) {
                questionForm.update(question);
            } else {
                Question newQuestion = new Question();
                questionForm.update(newQuestion);
                test.addQuestion(newQuestion);
            }
        }
    }
}
