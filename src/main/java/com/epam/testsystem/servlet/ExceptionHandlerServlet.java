package com.epam.testsystem.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ErrorHandler", urlPatterns = "/error")
public class ExceptionHandlerServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer statusCode = (Integer)
                req.getAttribute("javax.servlet.error.status_code");
        String requestUri = (String)
                req.getAttribute("javax.servlet.error.request_uri");
        Throwable throwable = (Throwable)
                req.getAttribute("javax.servlet.error.exception");
        LOG.error("errorStatus {} for requestUri: {}", statusCode, requestUri, throwable);

        if (statusCode == null) {
            return;
        }

        if(statusCode.equals(404)) {
            req.getRequestDispatcher("/WEB-INF/pages/common/404.jsp").forward(req, resp);
        } else if (statusCode.equals(500)) {
            req.getRequestDispatcher("/WEB-INF/pages/common/500.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/pages/common/error.jsp").forward(req, resp);
        }
    }
}
