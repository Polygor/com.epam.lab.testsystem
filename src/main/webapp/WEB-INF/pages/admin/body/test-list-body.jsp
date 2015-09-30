<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<table class="table table-striped">
    <thead>
    <tr>
        <th>#</th>
        <th><bean:message key="test.title"/></th>
        <th><bean:message key="test.pass.mark"/></th>
        <th><bean:message key="test.duration"/></th>
    </tr>
    </thead>
    <tbody>
    <nested:form action="/admin/testList">
        <nested:iterate name="testListForm" property="testForms" id="testForm" indexId="index">
            <c:set var="testId">
                <nested:write property="test.id"/>
            </c:set>
            <tr test-id="${testId}" onclick="selectTestFromTable(this)">
                <td><nested:write name="index"/></td>
                <td><nested:write property="test.title"/></td>
                <td><nested:write property="test.passMark"/></td>
                <td><nested:write property="test.duration"/></td>
            </tr>
        </nested:iterate>
    </nested:form>
    </tbody>
</table>
<div align="center">
    <html:link action="/admin/testAddEdit" styleClass="btn btn-primary btn-raised add-qwest-button">
        <bean:message key="admin.sidebar.navigation.test.add"/>
    </html:link>
</div>