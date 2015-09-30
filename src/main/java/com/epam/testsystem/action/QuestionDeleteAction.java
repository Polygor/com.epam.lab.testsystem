package com.epam.testsystem.action;

import com.epam.testsystem.form.QuestionForm;
import com.epam.testsystem.model.Test;
import com.epam.testsystem.service.QuestionService;
import com.epam.testsystem.service.TestService;
import org.apache.struts.action.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/admin/questionDelete")
public class QuestionDeleteAction extends Action {

    @Autowired
    TestService testService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionForm questionForm = (QuestionForm) form;
        Long questionId = questionForm.getId();
        Long testId = questionForm.getTestId();
        Test test = testService.findById(testId);
        test.removeQuestionById(questionId);
        testService.save(test);

        ActionRedirect redirect = new ActionRedirect(mapping.findForward("redirectToTest"));
        redirect.addParameter("id", testId);
        return redirect;
    }
}