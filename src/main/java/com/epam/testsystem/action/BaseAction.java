package com.epam.testsystem.action;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseAction<T> extends Action {

    @Override
    @SuppressWarnings("unchecked")
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String method = request.getMethod();
        if (method.equals("GET")) {
            return onGet(mapping, (T) form, request);
        } else if (method.equals("POST")) {
            return onPost(mapping, (T) form, request);
        }
        return null;
    }

    protected abstract ActionForward onPost(ActionMapping mapping, T form, HttpServletRequest request);

    protected abstract ActionForward onGet(ActionMapping mapping, T form, HttpServletRequest request);
}
