package com.epam.testsystem.action;

import com.epam.testsystem.form.*;
import com.epam.testsystem.model.Test;
import com.epam.testsystem.model.User;
import com.epam.testsystem.service.TestCaseService;
import com.epam.testsystem.service.TestService;
import com.epam.testsystem.service.UserService;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.epam.testsystem.util.SpringUtils.getCurrentlyAuthenticatedUser;

@Controller("/admin/usersTests")
public class UsersTestsAction extends BaseAction<UsersTestsForm> {

    @Autowired
    UserService userService;

    @Autowired
    TestService testService;

    @Autowired
    TestCaseService testCaseService;

    @Override
    protected ActionForward onPost(ActionMapping mapping, UsersTestsForm form, HttpServletRequest request) {
        return null;
    }

    @Override
    protected ActionForward onGet(ActionMapping mapping, UsersTestsForm form, HttpServletRequest request) {
        User userr = getCurrentlyAuthenticatedUser();
        request.getSession().setAttribute("user", userr);

        List<User> users = userService.findByRoleName("user");
        List<Test> tests = testService.findAll();
        List<UserForm> userForms = new ArrayList<>();
        List<TestForm> testForms = new ArrayList<>();
        for (User user : users) {
            userForms.add(new UserForm(user));
        }
        for (Test test : tests) {
            testForms.add(new TestForm(test));
        }
        for (User user : users) {
            form.putUserTestCases(user.getId(),testCaseService.findByUserId(user.getId()));
        }

        UserListForm userListForm = new UserListForm(userForms);
        TestListForm testListForm = new TestListForm(testForms);

        form.setTestListForm(testListForm);
        form.setUserListForm(userListForm);

        return mapping.findForward("success");
    }

}