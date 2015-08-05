<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'employee.label', default: 'Employee')}"/>
    <asset:javascript src="jquery.js"/>
    <asset:javascript src="jquery.datatables.min.js"/>
    <asset:javascript src="ajaxifiedDataTable.js"/>
    <asset:javascript src="application.js"/>
    <asset:stylesheet src="jquery.datatables.min.css"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-employee" class="skip" tabindex="-1">
    <g:message code="default.link.skip.label" default="Skip to content&hellip;"/>
</a>

<div class="nav" role="navigation">
    <ul>
        <li>
            <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
        </li>
    </ul>
</div>

<div id="list-employee" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table id="employeeListing" data-ajax-url="${createLink(controller: 'employee', action: 'ajax_fetchList')}" data-totalRecords="${totalRecords}">
        <thead>
        <tr>
            <td>First Name</td>
            <td>Last Name</td>
            <td>Email</td>
            <td>Age</td>
            <td>City</td>
            <td>Salary</td>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
</body>
</html>
