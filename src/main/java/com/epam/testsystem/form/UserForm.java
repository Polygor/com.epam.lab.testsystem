package com.epam.testsystem.form;

import com.epam.testsystem.model.User;

public class UserForm extends BaseForm<User> {

    private User user;
    private String oldPassword;
    private String newPassword;
    private String confirmPassword;

    public UserForm() {
    }

    public UserForm(User user) {
        this.user = user;
    }

    public UserForm(User user, String newPassword, String confirmPassword) {
        this.user = user;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User getUser() {
        if (this.user == null) {
            setUser(new User());
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void map(User user) {

    }

    @Override
    public void update(User user) {

    }

    public String getCredentials() {
        return user.getLastName() + " " + user.getFirstName().substring(0, 1) + ".";
    }
}
