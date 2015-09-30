package com.epam.testsystem.action;

import com.epam.testsystem.form.TestCaseForm;
import com.epam.testsystem.model.Answer;
import com.epam.testsystem.model.Test;
import com.epam.testsystem.model.TestCase;
import com.epam.testsystem.model.User;
import com.epam.testsystem.security.AuthenticatedUser;
import com.epam.testsystem.service.TestCaseService;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static com.epam.testsystem.util.SpringUtils.getCurrentlyAuthenticatedUser;

@Controller("/test")
public class UserTestCaseAction extends BaseAction<TestCaseForm> {

    @Autowired
    TestCaseService testCaseService;

    @Override
    protected ActionForward onPost(ActionMapping mapping, TestCaseForm form, HttpServletRequest request) {
        return null;
    }



    @Override
    protected ActionForward onGet(ActionMapping mapping, TestCaseForm form, HttpServletRequest request) {
        AuthenticatedUser user = getCurrentlyAuthenticatedUser();
        Long id = form.getId();
        List<TestCase> testCasesByUserId = testCaseService.findByUserId(user.getId());
        for (TestCase testCaseByUserId : testCasesByUserId) {
            if (testCaseByUserId.getId().equals(id)) {
                TestCase testCase = testCaseService.findById(id);
                form.map(testCase);
                ArrayList<Answer> answers = new ArrayList<>();
                Answer answer = new Answer();
                answer.setText("just testing");
                answer.setRight(false);
                answers.add(answer);
                form.setAnswers(answers);
                return mapping.findForward("success");
            } else {
                return mapping.findForward("error");
            }
        }
        return null;
    }
}
