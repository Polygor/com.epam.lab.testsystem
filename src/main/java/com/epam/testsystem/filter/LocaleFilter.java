package com.epam.testsystem.filter;

import org.apache.struts.Globals;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class LocaleFilter implements Filter {
    private static final String RU = "ru";
    private static final String EN = "en";
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String language = request.getParameter("language");
// Class works with languages
        if (language != null) {
            String locale = null;
            if (language.equals(RU)) {
                locale = RU;
            } else if (language.equals(EN)) {
                locale = EN;
            }
            if (locale != null) {
                request.getSession().setAttribute(Globals.LOCALE_KEY, new Locale(locale));
                String refererUrl = request.getHeader("referer");
                response.sendRedirect(refererUrl);
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }

}
