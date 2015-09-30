<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>
        <bean:message key="layout.title" />
    </title>
    <link rel="stylesheet" href="<c:url value='/webjars/bootstrap/3.3.5/css/bootstrap.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/static/css/style.css' />" />
</head>
<body>
    <div class="container">
        <nav class="navbar">
            <div class="container">
                <tiles:insert attribute="header"/>
            </div>
        </nav>
        <hr />

        <div class="row">
            <tiles:insert attribute="body"/>
        </div>
        <hr />

        <footer>
            <div class="container">
                <div class="row">
                    <tiles:insert attribute="footer"/>
                </div>
            </div>
        </footer>
    </div>
    <script src="<c:url value='/webjars/jquery/2.1.4/jquery.min.js' />"></script>
    <script src="<c:url value='/webjars/bootstrap/3.3.5/js/bootstrap.min.js' />"></script>
</body>
</html>