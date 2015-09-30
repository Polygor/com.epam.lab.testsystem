package com.epam.testsystem.form;

import org.apache.struts.action.ActionForm;

import java.util.ArrayList;
import java.util.List;

public class UserListForm extends ActionForm {
    private List<UserForm> userForms = new ArrayList<>();

    public UserListForm() {
    }

    public UserListForm(List<UserForm> userForms) {
        this.userForms = userForms;
    }

    public List<UserForm> getUserForms() {
        return userForms;
    }

    public void setUserForms(List<UserForm> userForms) {
        this.userForms = userForms;
    }
}
