package com.epam.testsystem.action;

import com.epam.testsystem.form.UserForm;
import com.epam.testsystem.model.User;
import com.epam.testsystem.service.UserService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/admin/removeUser")
public class UserRemoveAction extends Action {

    @Autowired
    public UserService userService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        UserForm userForm = (UserForm) form;
        Long userId = userForm.getId();
        userService.delete(userId);
        return mapping.findForward("redirect");
    }
}
