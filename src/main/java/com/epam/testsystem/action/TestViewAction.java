package com.epam.testsystem.action;

import com.epam.testsystem.form.TestForm;
import com.epam.testsystem.model.Test;
import com.epam.testsystem.service.TestService;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller("/admin/testView")
public class TestViewAction extends BaseAction<TestForm> {

    @Autowired
    TestService testService;

    @Override
    protected ActionForward onPost(ActionMapping mapping, TestForm form, HttpServletRequest request) {
        Long id = form.getId();
        testService.delete(id);
        return mapping.findForward("redirect");
    }

    @Override
    protected ActionForward onGet(ActionMapping mapping, TestForm form, HttpServletRequest request) {
        Long id = form.getId();
        if (id != null) {
            Test test = testService.findById(id);
            form.map(test);
        }
        return mapping.findForward("success");
    }
}
