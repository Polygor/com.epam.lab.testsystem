<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="nav nav-tabs" role="tablist">
    <li role="presentation" class="admin-tabs active"><a href="#home" aria-controls="home" role="tab"
                                                         data-toggle="tab"><bean:message
            key="admin.tests.for.users"/></a></li>
    <li role="presentation" class="admin-tabs"><a href="#profile" aria-controls="profile" role="tab"
                                                  data-toggle="tab"><bean:message key="admin.message.users"/></a></li>
</ul>


<div class="tab-content">

    <div role="tabpanel" class="tab-pane fade in active" id="home">
        <%--<nested:form action="/admin/studentList" styleClass="select-test-form">--%>
        <nested:form action="/admin/usersTests" styleClass="select-test-form">
            <div class="main">
                <div class="panel panel-default front-panel">
                    <span class="slide-in-out-button" onclick="slidePanel.doSlide(this)">
                        <div class="arrow-right"></div>
                    </span>

                    <input type="text" class="form-control search-row" onkeyup="findTest(this)"
                           placeholder="<bean:message key="admin.message.test.title"/>">

                    <div class="panel panel-default admin-test-list">
                        <nested:define id="testListForm" property="testListForm"/>
                        <nested:iterate name="testListForm" property="testForms" id="testForm">
                            <div>
                                <c:set var="testId">
                                    <nested:write property="test.id"/>
                                </c:set>
                                <nested:checkbox property="test.id" value="${testId}"/>
                                <label><nested:write property="test.title"/></label>
                            </div>
                        </nested:iterate>
                    </div>
                    <input type="button" class="btn btn-info admin-test-apply" onclick="setTestsForUser()"
                           value="Apply">

                </div>
                <div class="panel panel-default back-panel">
                    <table class="table stud-table">
                        <thead>
                        <tr>
                            <th>
                                <input type="text" class="form-control search-student-row" onkeyup="findUsers(this)"
                                       placeholder="<bean:message key="admin.message.user"/>">
                            </th>
                            <th><bean:message key="admin.message.appointed.tests"/></th>
                        </tr>
                        </thead>
                        <nested:define id="userListForm" property="userListForm"/>
                        <nested:iterate name="userListForm" property="userForms" id="userForm">
                            <tr id="<nested:write property="user.id"/>">
                                <td class="stud-td">
                                    <div class="stud-wide-info-drop-panel">
                                        <div>
                                            <div class="role"><nested:write property="user.role.name"/></div>
                                        </div>
                                        <div><label><bean:message key="user.first.name"/>:</label>

                                            <div><nested:write property="user.firstName"/></div>
                                        </div>
                                        <div><label><bean:message key="user.last.name"/>:</label>

                                            <div><nested:write property="user.lastName"/></div>
                                        </div>
                                        <div><label><bean:message key="user.email"/>:</label>

                                            <div><nested:write property="user.email"/></div>
                                        </div>
                                    </div>
                                    <div class="stud-wide-info" onclick="widePanel.doExpand(this)"><nested:write
                                            property="credentials"/></div>
                                    <div class="stud-cell"><nested:write property="credentials"/></div>
                                </td>
                                <td onmouseover="hoverTestLink(this)">
                                    <a role="button" data-toggle="collapse" data-parent="#accordion"
                                       href="#collapse<nested:write property="user.id"/>" aria-expanded="false"
                                       aria-controls="collapse<nested:write property="user.id"/>" class="collapsed"
                                       onmouseover="hoverTestLink(this)">
                                            <span>
                                                <div class="arrow-bottom"></div>
                                            </span>
                                    </a>

                                    <div id="collapse<nested:write property="user.id"/>" class="panel-collapse collapse"
                                         role="tabpanel" aria-labelledby="headingOne" aria-expanded="false"
                                         style="height: 0px;">
                                        <div class="panel-body">
                                            <c:set var="currentUserId">
                                                <nested:write property="user.id"/>
                                            </c:set>
                                            <ol>
                                                <nested:iterate name="usersTestsForm"
                                                                property="apointedTestsForEachUser" id="entry">
                                                    <c:set var="userId">
                                                        <nested:write name="entry" property="key"/>
                                                    </c:set>
                                                    <c:if test="${currentUserId == userId}">
                                                        <nested:iterate name="entry" property="value" id="testcase">
                                                            <li><label><nested:write name="testcase"
                                                                                     property="test.title"/></label>
                                                            </li>
                                                        </nested:iterate>
                                                    </c:if>
                                                </nested:iterate>
                                            </ol>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </nested:iterate>
                    </table>
                </div>
            </div>
        </nested:form>
    </div>

    <div role="tabpanel" class="tab-pane fade" id="profile">
        <nested:form action="/admin/userAddEdit">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>#</th>
                    <th><bean:message key="user.username"/></th>
                    <th><bean:message key="user.first.name"/></th>
                    <th><bean:message key="user.last.name"/></th>
                    <th><bean:message key="user.email"/></th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <nested:iterate name="userListForm" property="userForms" id="userForm" indexId="index">
                    <tr>
                        <td><nested:write name="index"/></td>
                        <td><nested:write property="user.username"/></td>
                        <td><nested:write property="user.firstName"/></td>
                        <td><nested:write property="user.lastName"/></td>
                        <td><nested:write property="user.email"/></td>
                        <c:set var="id">
                            <nested:write property="user.id"/>
                        </c:set>
                        <td><a class="btn btn-danger btn-raised add-qwest-button"
                               href="<c:url value="/admin/removeUser.do?id=${id}" />">X</a>
                        </td>
                    </tr>
                </nested:iterate>

                <tr id="createUserInputsId">
                    <td></td>
                    <td><html:text property="user.username"/></td>
                    <td><html:text property="user.firstName"/></td>
                    <td><html:text property="user.lastName"/></td>
                    <td><html:text property="user.email"/></td>
                </tr>

                </tbody>
            </table>
            <div align="center">
                <a class="btn btn-primary btn-raised add-qwest-button" onclick="createCancelStudent()"
                   id="createButton">
                    <bean:message key="admin.sidebar.navigation.student.add"/>
                </a>
                <html:submit styleClass="btn btn-primary btn-raised add-qwest-button" styleId="save">
                    <bean:message key="admin.sidebar.navigation.student.save"/>
                </html:submit>
                <a class="btn btn-default btn-raised add-qwest-button" onclick="createCancelStudent()" id="cancel">
                    <bean:message key="admin.sidebar.navigation.student.cancel"/>
                </a>
            </div>
            <input class="hidden" type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
        </nested:form>
    </div>
</div>

