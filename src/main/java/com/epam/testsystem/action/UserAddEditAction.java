package com.epam.testsystem.action;

import com.epam.testsystem.form.UserForm;
import com.epam.testsystem.model.Role;
import com.epam.testsystem.model.User;
import com.epam.testsystem.service.RoleService;
import com.epam.testsystem.service.UserService;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

import static com.epam.testsystem.util.jmail.MailHelper.sendEmail;

@Controller("/admin/userAddEdit")
public class UserAddEditAction extends BaseAction<UserForm> {

    @Autowired
    public UserService userService;
    @Autowired
    public RoleService roleService;
    @Autowired
    public Session session;

    @Override
    protected ActionForward onPost(ActionMapping mapping, UserForm form, HttpServletRequest request) {
        try {
            User user = form.getUser();
            Role userRole = roleService.findById((long) 2);
            user.setRole(userRole);
            String randomPassword = UUID.randomUUID().toString();
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(randomPassword);
            user.setPassword(encodedPassword);

            String locale = request.getSession().getAttribute(Globals.LOCALE_KEY).toString();
            sendEmail(session, user, randomPassword, locale);
            userService.save(user);
            return mapping.findForward("redirect");
        } catch (MessagingException | IOException e) {
            throw new ActionException(e);
        }
    }

    @Override
    protected ActionForward onGet(ActionMapping mapping, UserForm form, HttpServletRequest request) {
        return mapping.findForward("success");
    }
}
