package com.epam.testsystem.model;

import com.epam.testsystem.util.LocalDateTimePersistenceConverter;
import com.sun.istack.internal.Nullable;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class TestCase extends BaseEntity {
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime startedAt;
    @Convert(converter = LocalDateTimePersistenceConverter.class)
    private LocalDateTime dueDate;
    @ManyToOne
    private Test test;
    @ManyToOne
    private User user;
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Answer> answers;
// TESTCASE - When admin gives the test to certain user testcase will be created
    public TestCase() {
    }

    public TestCase(User user, Test test) {
        this.user = user;
        this.test = test;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
