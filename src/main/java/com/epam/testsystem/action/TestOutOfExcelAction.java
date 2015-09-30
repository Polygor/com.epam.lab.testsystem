package com.epam.testsystem.action;

import com.epam.testsystem.model.Answer;
import com.epam.testsystem.model.Question;
import com.epam.testsystem.model.User;
import com.epam.testsystem.service.QuestionService;
import com.epam.testsystem.service.TestService;
import com.epam.testsystem.util.ExcelLoaderDocument;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epam.testsystem.util.SpringUtils.getCurrentlyAuthenticatedUser;
@Controller("/admin/getQuestionsOutOfFile")
public class TestOutOfExcelAction extends Action {
    // This class works with xls files and parses them
    @Autowired
    TestService testService;
    @Autowired
    QuestionService questionService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        User user = getCurrentlyAuthenticatedUser();
        request.getSession().setAttribute("user", user);
        //TODO NEED TO TEST
        ExcelLoaderDocument excelLoaderDocument = new ExcelLoaderDocument();
        List<String> questions = excelLoaderDocument.getQuestionsData();
        List<String> answers = excelLoaderDocument.getAnswersData();
        List<String> correctAnswers = excelLoaderDocument.getCorrectAnswers();
        for (String item : questions) {
            Question question = new Question();
            question.setTitle(item);
            for (String item2 : answers) {
                Answer answer = new Answer();
                answer.setText(item2);
                List<Answer> answerEntities = new ArrayList<>();
                if (item2.equals(correctAnswers.iterator().next())) {
                    answer.setRight(true);
                } else {
                    answer.setRight(false);
                }
                answerEntities.add(answer);
                question.setAnswers(answerEntities);
                questionService.save(question);

            }

        }
        return mapping.findForward("success");
    }
}
