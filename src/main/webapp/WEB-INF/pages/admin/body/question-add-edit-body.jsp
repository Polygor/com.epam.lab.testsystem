<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${not empty testForm}">
    <nested:define id="questionForm" />
</c:if>
<nested:form action="/admin/questionAddEdit">
    <c:if test="${not empty testForm}">
        <input class="hidden" type="hidden"
               name="testId"
               value="${testForm.id}"/>
    </c:if>
    <c:set var="questionId">
        <nested:write property='id' />
    </c:set>
    <div class="question-form" id="${questionId}">
        <div class="input-group">
            <c:if test="${not empty testForm}">
                <span class="input-group-addon">
                    <a href="/testsystem/admin/questionDelete.do?id=${questionId}&testId=${testForm.id}">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                </span>
            </c:if>
            <nested:textarea property="title" rows="3" styleClass="form-control vresize">
            </nested:textarea>
        </div>
        <div class="answers">
            <nested:iterate property="answers">
                <nested:text property="id" readonly="true" styleClass="hidden"/>
                <div class="answer">
                    <div>
                        <div class="input-group">
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-remove" onclick="removeAnswer(this)"
                              aria-hidden="true"></span>
                    </span>
                            <nested:textarea property="text" rows="3" styleClass="form-control vresize">
                            </nested:textarea>
                    <span class="input-group-addon">
                        <nested:checkbox property="right">
                        </nested:checkbox>
                    </span>
                        </div>
                    </div>
                </div>
            </nested:iterate>
        </div>
        <a onclick="addAnswer(this)" href="javascript:void(0)"
           class="btn btn-primary btn-raised add-ans-button"><bean:message key="button.add.answer"/></a>

        <div class="clearfix"></div>
        <div class="text-center">
            <nested:text property="id" readonly="true" styleClass="hidden"/>
            <input class="hidden" type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"
                   enctype="multipart/form-data"
                    />
            <nested:submit>
                <bean:message key="button.save"/>
            </nested:submit>
        </div>
    </div>
</nested:form>