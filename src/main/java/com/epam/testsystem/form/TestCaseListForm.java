package com.epam.testsystem.form;

import org.apache.struts.action.ActionForm;

import java.util.ArrayList;
import java.util.List;

public class TestCaseListForm extends ActionForm {
    private List<TestCaseForm> testCaseForms = new ArrayList<>();

    public TestCaseListForm() {
    }

    public TestCaseListForm(List<TestCaseForm> testCaseForms) {
        this.testCaseForms = testCaseForms;
    }

    public List<TestCaseForm> getTestCaseForms() {
        return testCaseForms;
    }

    public void setTestCaseForms(List<TestCaseForm> testCaseForms) {
        this.testCaseForms = testCaseForms;
    }

    public void setAddTestCaseForm(TestCaseForm addTestCaseForm) {
        this.testCaseForms.add(addTestCaseForm);
    }
}
