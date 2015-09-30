package com.epam.testsystem.form;

import com.epam.testsystem.model.Answer;

public class AnswerForm extends BaseForm<Answer> {
    private String text;
    private boolean right;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    @Override
    public void map(Answer answer) {
        this.id = answer.getId();
        this.text = answer.getText();
        this.right = answer.isRight();
    }

    @Override
    public void update(Answer answer) {
        answer.setText(text);
        answer.setRight(right);
    }
}
