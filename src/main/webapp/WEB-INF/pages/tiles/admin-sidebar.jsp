<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="nested" uri="http://struts.apache.org/tags-nested" %>

<h4><bean:message key="admin.sidebar.navigation.test" /></h4>
<ul class="nav nav-pills nav-stacked">
    <li>
        <html:link action="/admin/testList">
            <bean:message key="admin.sidebar.navigation.test.list" />
        </html:link>
    </li>
    <li>
        <html:link action="/admin/usersTests">
            <bean:message key="admin.sidebar.navigation.students" />
        </html:link>
    </li>
    <li>
        <html:link action="/changePassword" styleClass="btn btn-primary change-password-button">
            <bean:message key="change.password"/>
        </html:link>
    </li>
</ul>