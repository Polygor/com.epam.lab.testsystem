package com.epam.testsystem.action;

import com.epam.testsystem.form.QuestionListForm;
import com.epam.testsystem.model.Question;
import com.epam.testsystem.service.QuestionService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller("/admin/questionList")
public class QuestionListAction extends Action {

    @Autowired
    QuestionService questionService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        List<Question> questions = questionService.findAll();
        QuestionListForm questionListForm = (QuestionListForm) form;
        questionListForm.setQuestions(questions);
        return mapping.findForward("success");
    }
}
