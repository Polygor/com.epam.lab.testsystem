<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="">

    </c:when>
    <c:otherwise>
        <html:form action="/test">
            <nested:iterate property="questions" indexId="qindex">
                <c:set var="qIndex">
                    <bean:write name="qindex"/>
                </c:set>
                <div id="question${qindex + 1}" class="question <c:if test="${qindex > 0}">hidden</c:if> ">
                    <div class="quest-text">
                        <nested:write property="title"/>
                    </div>
                    <c:set var="isMultiple">
                        <nested:write property="multipleAnswers"/>
                    </c:set>

                    <div style="float: right" class="text-center">
                        <label><bean:message key="answer.not.sure"/></label>

                        <div class="onoffswitch">
                            <input type="checkbox" class="onoffswitch-checkbox" id="switch${qindex + 1}">
                            <label class="onoffswitch-label" for="switch${qindex + 1}">
                                <span class="onoffswitch-inner"></span>
                                <span class="onoffswitch-switch"></span>
                            </label>
                        </div>
                    </div>

                    <div class="answers">
                        <nested:iterate property="answers" indexId="aindex">

                            <c:set var="aIndex">
                                <bean:write name="aindex"/>
                            </c:set>
                            <div class="col-sm-12">
                                <c:if test="${isMultiple eq 0}">
                                    <div class="custom-btn-block">
                                        <label for="q${qIndex}a${aIndex}" class="button">
                                            <span current-index="${aIndex}"></span>
                                        </label>
                                        <input name="answers" onclick="selectAnswer(this)" id="q${qIndex}a${aIndex}"
                                               type="radio">
                                <span>
                                    <nested:write property="text"/>
                                </span>
                                    </div>
                                </c:if>
                                <c:if test="${isMultiple eq 1}">
                                    <div class="custom-btn-block">
                                        <label for="q${qIndex}a${aIndex}" class="button">
                                            <span current-index="${aIndex}"></span>
                                        </label>
                                        <input name="answers[${aIndex}]" onclick="selectAnswer(this)"
                                               id="q${qIndex}a${aIndex}" type="checkbox">

                                <span>
                                    <nested:write property="text"/>
                                </span>
                                    </div>
                                </c:if>
                            </div>
                        </nested:iterate>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </nested:iterate>
        </html:form>
        <nav>
            <ul class="pager">
                <li><a href="#" onclick="switchQuestionToPrevious()"><span class="glyphicon glyphicon-menu-left"></span></a>
                </li>
                <li><a href="#" onclick="switchQuestionToNext()"><span
                        class="glyphicon glyphicon-menu-right"></span></a></li>
            </ul>
        </nav>
    </c:otherwise>
</c:choose>