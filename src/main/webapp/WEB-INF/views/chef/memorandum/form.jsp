  
<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
<jstl:choose>

	<jstl:when test="${command == 'show'}">	
	<h2> <acme:message code="chef.memorandum.list.label.fineDish.details"/> </h2>
	
	<acme:input-textbox code="chef.memorandum.form.label.seqNumber" path="seqNumber"/>
	<acme:input-moment code="chef.memorandum.form.label.instantiationMoment" path="instantiationMoment"/>	
	<acme:input-textarea  code="chef.memorandum.form.label.report" path="report"/>
	<acme:input-url code="chef.memorandum.form.label.link" path="link"/>
	<br>
	<h2> <acme:message code="chef.memorandum.list.label.fineDish"/> </h2>
	<br>
	<acme:input-textbox code="chef.fineDish.list.label.fineDish.identify.status" path="fineDish.status"/>
	<acme:input-textbox code="chef.fineDish.list.label.fineDish.identify.code" path="fineDish.code"/>
	<acme:input-textbox code="chef.fineDish.list.label.fineDish.identify.request" path="fineDish.request"/>
	<acme:input-money code="chef.fineDish.list.label.fineDish.identify.budget" path="fineDish.budget"/>
	<acme:input-textbox code="chef.fineDish.list.label.fineDish.identify.startsAt" path="fineDish.startsAt"/>
	<acme:input-textbox code="chef.fineDish.list.label.fineDish.identify.finishesAt" path="fineDish.finishesAt"/>
	<acme:input-textbox code="chef.fineDish.list.label.fineDish.identify.link" path="fineDish.link"/>
	
	</jstl:when>	
	<jstl:when test="${command == 'create'}">
	<acme:input-textbox readonly="true" code="chef.memorandum.form.label.seqNumber" path="seqNumber"/>
	<acme:input-moment readonly="true" code="chef.memorandum.form.label.instantiationMoment" path="instantiationMoment"/>	
	<acme:input-textarea  code="chef.memorandum.form.label.report" path="report"/>
	<acme:input-url code="chef.memorandum.form.label.link" path="link"/>
	<acme:input-checkbox code="chef.memorandum.form.label.confirm" path="confirm"/>
	<acme:submit code="chef.memorandum.form.button.create" action="/chef/memorandum/create"/>
	</jstl:when>
</jstl:choose>

</acme:form>