<%@ page import="com.ajaxified.datatable.demo.Employee" %>



<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'address', 'error')} required">
	<label for="address">
		<g:message code="employee.address.label" default="Address" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="address" required="" value="${employeeInstance?.address}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'dob', 'error')} required">
	<label for="dob">
		<g:message code="employee.dob.label" default="Dob" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dob" precision="day"  value="${employeeInstance?.dob}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="employee.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${employeeInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="employee.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${employeeInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'office', 'error')} required">
	<label for="office">
		<g:message code="employee.office.label" default="Office" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="office" required="" value="${employeeInstance?.office}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: employeeInstance, field: 'salary', 'error')} required">
	<label for="salary">
		<g:message code="employee.salary.label" default="Salary" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="salary" value="${fieldValue(bean: employeeInstance, field: 'salary')}" required=""/>

</div>

