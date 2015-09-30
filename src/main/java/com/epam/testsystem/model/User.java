package com.epam.testsystem.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "DBUSER"
//        uniqueConstraints = @UniqueConstraint(columnNames="USERNAME")
)
public class User extends BaseEntity {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    @ManyToOne
    private Role role;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TestCase> testCases;

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getRoleName() {
        return this.getRole().getName();
    }

    public boolean containsTestCase(Long testId) {
        if (testCases == null || testId == null) {
            return false;
        }

        for (TestCase testCase : testCases) {
            if (testId.equals(testCase.getTest().getId())) {
                return true;
            }
        }
        return false;
    }

    public void addTestCase(TestCase testCase) {
        if (testCases == null) {
            testCases = new ArrayList<>();
        }

        this.testCases.add(testCase);
    }
}
