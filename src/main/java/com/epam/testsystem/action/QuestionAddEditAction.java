package com.epam.testsystem.action;

import com.epam.testsystem.form.QuestionForm;
import com.epam.testsystem.model.Question;
import com.epam.testsystem.model.Test;
import com.epam.testsystem.model.User;
import com.epam.testsystem.service.QuestionService;
import com.epam.testsystem.service.TestService;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import static com.epam.testsystem.util.SpringUtils.getCurrentlyAuthenticatedUser;

@Controller("/admin/questionAddEdit")
public class QuestionAddEditAction extends BaseAction<QuestionForm> {

    @Autowired
    QuestionService questionService;

    @Autowired
    TestService testService;

    @Override
    protected ActionForward onPost(ActionMapping mapping, QuestionForm form, HttpServletRequest request) {
        User user = getCurrentlyAuthenticatedUser();
        Long id = form.getId();
        Question question;
        if(id == null || id.equals(0L)) {
            question = new Question();
            question.setCreatedBy(user);
        } else {
            question = questionService.findById(form.getId());
            question.setUpdatedBy(user);
        }

        form.update(question);
        Long testId = form.getTestId();
        if(testId != null) {
            Test test = testService.findById(testId);
            test.addQuestion(question);
            Test saved = testService.save(test);
            ActionRedirect redirect = new ActionRedirect(mapping.findForward("redirectToTest"));
            redirect.addParameter("id", saved.getId());
            return redirect;
        } else {
            Question saved = questionService.save(question);
            ActionRedirect redirect = new ActionRedirect(mapping.findForward("redirect"));
            redirect.addParameter("id", saved.getId());
            return redirect;
        }
    }

    @Override
    protected ActionForward onGet(ActionMapping mapping, QuestionForm form, HttpServletRequest request) {
        Long id = form.getId();
        if (id != null) {
            Question question = questionService.findById(id);
            form.map(question);
        }
        return mapping.findForward("success");
    }
}
