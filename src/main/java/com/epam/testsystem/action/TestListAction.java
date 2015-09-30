package com.epam.testsystem.action;

import com.epam.testsystem.form.TestForm;
import com.epam.testsystem.form.TestListForm;
import com.epam.testsystem.model.Test;
import com.epam.testsystem.model.User;
import com.epam.testsystem.service.TestService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static com.epam.testsystem.util.SpringUtils.getCurrentlyAuthenticatedUser;

@Controller("/admin/testList")
public class TestListAction extends Action {

    @Autowired
    TestService testService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        User userr = getCurrentlyAuthenticatedUser();
        request.getSession().setAttribute("user", userr);

        List<Test> tests = testService.findAll();
        TestListForm testListForm = (TestListForm) form;
        List<TestForm> testForms = new ArrayList<>();

        for (Test test : tests) {
            testForms.add(new TestForm(test));
        }

        testListForm.setTestForms(testForms);
        return mapping.findForward("success");
    }
}
