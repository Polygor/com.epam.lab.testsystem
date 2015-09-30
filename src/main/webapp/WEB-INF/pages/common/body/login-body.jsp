<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--<form role="form" action="<c:url value='/login'/>" method="post">--%>
<%--<div class="text-center">--%>
<%--<input type="text" name="username" />--%>
<%--<input type="password" name="password" />--%>
<%--<input class="hidden" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--<html:submit>--%>
<%--<bean:message key="button.login" />--%>
<%--</html:submit>--%>
<%--</div>--%>
<%--</form>--%>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <div class="account-wall">
                <img class="profile-img"
                     src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                     alt="">

                <div class="text-center text-danger">
                    <c:if test="${not empty param.error}">
                        <bean:message key="error.wrong.username.password"/>
                    </c:if>
                </div>
                <form role="form" action="<c:url value='/login'/>" method="post" class="form-signin">
                    <div class="text-center">
                        <input name="username" type="text" class="form-control" placeholder="User name" required
                               autofocus>
                        <input name="password" type="password" class="form-control" placeholder="Password" required>
                        <input class="hidden" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <html:submit styleClass="btn btn-lg btn-primary btn-block">
                            <bean:message key="button.login"/>
                        </html:submit>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
