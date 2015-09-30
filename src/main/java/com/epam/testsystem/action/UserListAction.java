package com.epam.testsystem.action;

import com.epam.testsystem.service.UserService;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/admin/studentList")
public class UserListAction extends Action {

    @Autowired
    UserService userService;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
//        List<User> users = userService.findByRoleName("user");
//        List<User> undeletedUsers = new ArrayList<>();
//        for (User user : users) {
//            if (!user.isDeleted()) {
//                undeletedUsers.add(user);
//            }
//        }
//        UserListForm userListForm = (UserListForm) form;
//        userListForm.setUsers(users);
        return mapping.findForward("success");
    }
}