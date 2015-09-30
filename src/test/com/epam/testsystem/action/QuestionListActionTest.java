package com.epam.testsystem.action;

import com.epam.testsystem.form.QuestionListForm;
import com.epam.testsystem.model.Question;
import com.epam.testsystem.repository.QuestionRepository;
import com.epam.testsystem.service.QuestionService;
import junit.framework.TestCase;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionListActionTest {


    @InjectMocks
    private QuestionListAction questionListAction;
    @Mock
    private QuestionService questionService;
    @Mock
    private QuestionRepository questionRepository;
    @Mock
    private QuestionListForm questionListForm;
    @Mock
    private HttpServletRequest request;
    @Mock
    private HttpServletResponse response;
    @Mock
    private ActionMapping mapping;

    @Test
    public void testQuestionListActionSuccess() throws Exception {
        ArrayList<Question> questions = new ArrayList<>();
        Question questionOne = new Question();
        questionOne.setId((long) 1);
        Question questionTwo = new Question();
        questions.add(questionOne);
        questions.add(questionTwo);

        questionListForm.setQuestions(questions);
        when(questionService.findAll()).thenReturn(questions);

        when(mapping.findForward("success")).thenReturn(new ActionForward("success"));

        ActionForward forward = questionListAction.execute(mapping, questionListForm, request, response);

        verify(questionService).findAll();
        verify(mapping).findForward("success");

        assertEquals("success", forward.getPath());
    }
}