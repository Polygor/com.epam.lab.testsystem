package com.epam.testsystem.form;

import com.epam.testsystem.model.Test;
import org.apache.struts.action.ActionForm;

import java.util.ArrayList;
import java.util.List;

public class TestListForm extends ActionForm {
    private List<TestForm> testForms = new ArrayList<>();

    public TestListForm() {
    }

    public TestListForm(List<TestForm> testForms) {
        this.testForms = testForms;
    }

    public List<TestForm> getTestForms() {
        return testForms;
    }

    public void setTestForms(List<TestForm> testForms) {
        this.testForms = testForms;
    }
}
