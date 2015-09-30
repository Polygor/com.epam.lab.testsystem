<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean" %>

<div>
    <nested:form action="/saveNewPassword" method="post" styleClass="form-signin">
        <div class="account-wall">
            <div class="margin-left-right-5px">
                <h2 class="font-size-20px" align="center">
                    <bean:message key="change.password"/>
                </h2>

                <div>
                    <div class="text-center">
                        <input type="password" name="oldPassword" value="" id="oldPassword"
                               class="form-control padding-top-bottom-5px font-size-14px"
                               placeholder="<bean:message key="old.password"/>">
                        <input type="password" name="newPassword" value="" id="newPassword"
                               class="form-control padding-top-bottom-5px font-size-14px"
                               placeholder="<bean:message key="new.password"/>">
                        <input type="password" name="confirmPassword" value="" id="confirmPassword"
                               class="form-control padding-top-bottom-5px font-size-14px"
                               placeholder="<bean:message key="confirm.password"/>">
                        <input class="hidden" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <html:submit styleClass="btn btn-lg btn-primary btn-block padding-top-bottom-5px font-size-14px"
                                     styleId="savePassword">
                            <bean:message key="button.save"/>
                        </html:submit>
                    </div>
                </div>
            </div>
        </div>
    </nested:form>
</div>
