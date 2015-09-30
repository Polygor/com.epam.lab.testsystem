package com.epam.testsystem.dto;


import java.util.List;
// DTO - Data transfer object.
public class UserTestsDto {
    private String userId;
    private List<String> testIds;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTestIds() {
        return testIds;
    }

    public void setTestIds(List<String> testIds) {
        this.testIds = testIds;
    }
}
