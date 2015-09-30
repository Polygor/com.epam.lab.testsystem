package com.epam.testsystem.form;


import com.epam.testsystem.model.TestCase;
import org.apache.struts.action.ActionForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsersTestsForm extends ActionForm {

    private Map<Long, List<TestCase>> apointedTestsForEachUser = new HashMap<>();

    private TestListForm testListForm;
    private UserListForm userListForm;

    public UsersTestsForm() {
    }

    public UsersTestsForm(TestListForm testListForm, UserListForm userListForm) {
        this.testListForm = testListForm;
        this.userListForm = userListForm;
    }

    public UsersTestsForm(Map<Long, List<TestCase>> apointedTestsForEachUser, TestListForm testListForm, UserListForm userListForm) {
        this.apointedTestsForEachUser = apointedTestsForEachUser;
        this.testListForm = testListForm;
        this.userListForm = userListForm;
    }

    public Map<Long, List<TestCase>> getApointedTestsForEachUser() {
        return apointedTestsForEachUser;
    }

    public void setApointedTestsForEachUser(Map<Long, List<TestCase>> apointedTestsForEachUser) {
        this.apointedTestsForEachUser = apointedTestsForEachUser;
    }

    public TestListForm getTestListForm() {
        return testListForm;
    }

    public void setTestListForm(TestListForm testListForm) {
        this.testListForm = testListForm;
    }

    public UserListForm getUserListForm() {
        return userListForm;
    }

    public void setUserListForm(UserListForm userListForm) {
        this.userListForm = userListForm;
    }

    public void putUserTestCases(Long userId, List<TestCase> testIds){
        apointedTestsForEachUser.put(userId, testIds);
    }
}
