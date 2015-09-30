<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="navbar-header">
    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#languages-navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
    </button>
    <html:link action="/login">
        <bean:message key="layout.title" />
    </html:link>
    <br>
<div>
    <html:link action="/logout">
        <bean:message key="header.logout" />
    </html:link>
</div>



<div class="collapse navbar-collapse" id="languages-navbar-collapse">
    <ul class="nav navbar-nav navbar-right">
        <li class="active">
            <bean:define id="en" value="en" />
            <html:link action="login" paramId="language" paramName="en">
                <bean:message key="header.language.english" />
            </html:link>
        </li>
        <li>
            <bean:define id="ru" value="ru" />
            <html:link action="login" paramId="language" paramName="ru">
                <bean:message key="header.language.russian" />
            </html:link>
        </li>
    </ul>
    <c:if test="${not empty user}">
        <div class="user-in-system">
            <span><a href="<c:url value="/logout.do"/>" title="<bean:message key="header.logout" />"><img src="<c:url value="/static/image/logout.png"/>"></a></span>
            <label>${user.role.name}: ${user.username} ${user.lastName}</label>
        </div>
    </c:if>
</div>