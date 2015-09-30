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
            <th><bean:message key="question.title" /> </th>
            <th><bean:message key="question.weight"/> </th>
        </tr>
    </thead>
    <tbody>
    <logic:iterate name="questionListForm" property="questions" id="question" indexId="index">
        <tr>
            <td><bean:write name="index"/></td>
            <td><bean:write name="question" property="title"/></td>
            <td><bean:write name="question" property="weight"/></td>
        </tr>
    </logic:iterate>
    </tbody>
</table>
