<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<nested:form action="/admin/testAddEdit">
    <div class="form-inline col-sm-4 text-center form-group">
        <label><bean:message key="test.title"/></label>
        <html:text property="title" style="width: 100%;" styleClass="form-control"/>
    </div>

    <div class="form-inline col-sm-4 text-center form-group">
        <label><bean:message key="test.pass.mark"/></label>
        <html:text property="passMark" style="width: 100%;" styleClass="form-control"/>
    </div>

    <div class="form-inline col-sm-4 text-center form-group">
        <label><bean:message key="test.duration"/></label>
        <html:text property="duration" style="width: 100%;" styleClass="form-control"/>
    </div>
    <div class="text-center">
        <html:text property="id" readonly="true" styleClass="hidden"/>
        <input class="hidden" type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <html:submit styleClass="btn btn-default btn-raised add-qwest-button">
            <bean:message key="button.save"/>
        </html:submit>
    </div>
</nested:form>

<div class="main-quest-panel col-sm-12">
    <div class="questions">
        <nested:iterate name="testForm" property="questions" id="questionForm" indexId="index">
            <c:set var="i">
                <nested:write name="index"/>
            </c:set>
            <div class="question-list-item">
                <label>Вопрос № ${i + 1}</label>
                <jsp:include page="question-add-edit-body.jsp"/>
            </div>
        </nested:iterate>
    </div>
    <div class="text-center">
        <a href="javascript:void(0)" class="btn btn-primary btn-raised add-qwest-button"><bean:message
                key="button.add.question"/></a>
    </div>
</div>


