package com.epam.testsystem.util;

import com.epam.testsystem.security.AuthenticatedUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SpringUtils {
    public static AuthenticatedUser getCurrentlyAuthenticatedUser() {
        return (AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Spring security util, allows to get Currently Authenticated user
    }
}
