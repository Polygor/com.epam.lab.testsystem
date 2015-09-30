package com.epam.testsystem.form;

import com.epam.testsystem.model.Answer;
import com.epam.testsystem.model.Question;

import java.util.List;

public class QuestionForm extends BaseForm<Question> {
    private Long testId;
    private Integer multipleAnswers = 0;
    private String title;
    private Double weight;

    private List<AnswerForm> answers = new StrutsArrayList<>(AnswerForm.class);

    public List<AnswerForm> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerForm> answers) {
        this.answers = answers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public int getMultipleAnswers() {
        return multipleAnswers;
    }

    public void setMultipleAnswers(int multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public void setMultipleAnswers(Integer multipleAnswers) {
        this.multipleAnswers = multipleAnswers;
    }

    public void map(Question question) {
        this.id = question.getId();
        this.title = question.getTitle();
        this.weight = question.getDifficulty();
        int trueAnswers = 0;
        for (Answer answer : question.getAnswers()) {
            AnswerForm answerForm = new AnswerForm();
            answerForm.map(answer);
            answers.add(answerForm);
            if (answer.isRight()) trueAnswers++;
        }
        if (trueAnswers > 1) multipleAnswers = 1;
    }

    @Override
    public void update(Question question) {
        question.setTitle(title);
        question.setDifficulty(weight);
        for (AnswerForm answerForm : answers) {
            Long answerFormId = answerForm.getId();
            Answer answer = question.getAnswerById(answerFormId);
            if (answer != null) {
                answerForm.update(answer);
            } else {
                Answer newAnswer = new Answer();
                answerForm.update(newAnswer);
                question.addAnswer(newAnswer);
                newAnswer.setQuestion(question);
            }
        }
    }
}
