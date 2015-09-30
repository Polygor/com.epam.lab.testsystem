package com.epam.testsystem.action;

import com.epam.testsystem.form.TestCaseForm;
import com.epam.testsystem.model.TestCase;
import com.epam.testsystem.model.User;
import com.epam.testsystem.repository.TestCaseRepository;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.apache.struts.action.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import static com.epam.testsystem.util.SpringUtils.getCurrentlyAuthenticatedUser;

@Controller("/index")
public class UserIndexAction extends Action {

    @Autowired
    private TestCaseRepository testCaseRepository;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = getCurrentlyAuthenticatedUser();

        List<TestCase> testCasesByUserId = testCaseRepository.findByUser_Id(user.getId());
        TestCaseForm testCaseForm = (TestCaseForm) form;
        for (TestCase testCase : testCasesByUserId) {
            if (testCase.getStartedAt() == null) {
                testCaseForm.getNotCompletedTestCases().add(testCase);
            } else {
                testCaseForm.getCompletedTestCases().add(testCase);
            }
            System.out.println(testCaseForm.getCompletedTestCases());
        }

        request.getSession().setAttribute("user", user);
        return mapping.findForward("success");
    }
}
