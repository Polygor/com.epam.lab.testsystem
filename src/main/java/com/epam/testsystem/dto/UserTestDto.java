package com.epam.testsystem.dto;

public class UserTestDto {
// DTO - Data transfer object.
    private Long userId;
    private Long testId;

    public UserTestDto(Long userId, Long testId) {
        this.userId = userId;
        this.testId = testId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
