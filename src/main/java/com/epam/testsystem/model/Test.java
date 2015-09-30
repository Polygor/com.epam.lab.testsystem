package com.epam.testsystem.model;

import com.epam.testsystem.util.DurationToStringConverter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Test extends BaseEntity {
    private String title;
    private Double passMark;
//Each test can be created by admin, passMark, title and duration can be created. Also you can connect certain questions with certain test
    @Convert(converter = DurationToStringConverter.class)
    private Duration duration;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Question> questions;

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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Question getQuestionById(Long id) {
        if (questions == null || id == null) {
            return null;
        }

        for (Question question : questions) {
            if (id.equals(question.getId())) {
                return question;
            }
        }
        return null;
    }

    public void addQuestion(Question question) {
        if (questions == null) {
            questions = new ArrayList<>();
        }

        this.questions.add(question);
    }

    public boolean removeQuestionById(Long questionId) {
        if (questions == null || questionId == null) {
            return false;
        }

        for (Question question : questions) {
            if (questionId.equals(question.getId())) {
                questions.remove(question);
                return true;
            }
        }
        return false;
    }
}
