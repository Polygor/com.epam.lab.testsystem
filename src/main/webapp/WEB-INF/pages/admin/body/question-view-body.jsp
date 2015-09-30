<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html:form action="/admin/questionView">
    <div class="col-sm-2">
        <bean:message key="question.title" />
    </div>
    <div class="col-sm-10">
        <bean:write name="questionForm" property="title" />
    </div>
    <div class="clearfix"></div>
    <br />

    <div class="col-sm-2">
        <bean:message key="question.weight" />
    </div>
    <div class="col-sm-10">
        <bean:write name="questionForm" property="weight" />
    </div>
    <div class="clearfix"></div>
    <br />
    <div class="text-center">
        <html:text property="id" readonly="true" styleClass="hidden" />
        <input class="hidden" type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>

        <nested:link action="/admin/questionAddEdit" paramId="id" paramProperty="id">
            <bean:message key="link.edit" />
        </nested:link>
        <html:submit>
            <bean:message key="button.delete" />
        </html:submit>
    </div>
</html:form>
