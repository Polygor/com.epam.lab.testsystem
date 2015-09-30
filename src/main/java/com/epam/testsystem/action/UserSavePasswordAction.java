package com.epam.testsystem.action;

import com.epam.testsystem.form.UserForm;
import com.epam.testsystem.model.User;
import com.epam.testsystem.service.UserService;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import static com.epam.testsystem.util.SpringUtils.getCurrentlyAuthenticatedUser;

@Controller("/saveNewPassword")
public class UserSavePasswordAction extends BaseAction<UserForm> {

    @Autowired
    public UserService userService;

    @Override
    protected ActionForward onPost(ActionMapping mapping, UserForm form, HttpServletRequest request) {
        User user = getCurrentlyAuthenticatedUser();
        User userById = userService.findById(user.getId());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(form.getOldPassword(), user.getPassword())) return mapping.findForward("saveError");
        String newPassword = form.getNewPassword();
        if (!newPassword.equals(form.getConfirmPassword())) return mapping.findForward("saveError");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);

        userById.setPassword(encodedPassword);
        userService.save(userById);
        return mapping.findForward("redirect");
    }

    @Override
    protected ActionForward onGet(ActionMapping mapping, UserForm form, HttpServletRequest request) {
        return mapping.findForward("success");
    }
}
