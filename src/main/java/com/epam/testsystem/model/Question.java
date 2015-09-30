package com.epam.testsystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Question extends BaseEntity {
    private String title;
    private Double difficulty;
// Question, has rate system. Has difficulty, difficulty should influence on result of passing test
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Image> images;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Double weight) {
        this.difficulty = weight;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Answer getAnswerById(Long id) {
        if (answers == null || id == null) {
            return null;
        }

        for (Answer answer : answers) {
            if(id.equals(answer.getId())) {
                return answer;
            }
        }
        return null;
    }

    public void addAnswer(Answer answer) {
        if (answers == null) {
            answers = new ArrayList<>();
        }

        this.answers.add(answer);
    }
}
