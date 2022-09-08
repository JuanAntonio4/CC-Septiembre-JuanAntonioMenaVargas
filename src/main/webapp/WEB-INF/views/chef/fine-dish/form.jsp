<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="chef.finedish.form.label.status" path="status" readonly="true"/>
	<acme:input-textbox code="chef.finedish.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="chef.finedish.form.label.request" path="request" readonly="true"/>
	<acme:input-money code="chef.finedish.form.label.budget" path="budget" readonly="true"/>
	<acme:input-money code="epicure.label.moneyExchange" path="moneyExchange" readonly="true" />
	<acme:input-textbox code="chef.finedish.form.label.startsAt" path="startsAt" readonly="true"/>
	<acme:input-textbox code="chef.finedish.form.label.finishesAt" path="finishesAt" readonly="true"/>
	<acme:input-url code="chef.finedish.form.label.link" path="link" readonly="true"/>

	<acme:input-textbox code="chef.finedish.form.label.epicureorganisation" path="epicure.organisation" readonly="true"/>
	<acme:input-textbox code="chef.finedish.form.label.epicureassertion" path="epicure.assertion" readonly="true"/>
	<acme:button code="chef.finedish.form.buttom.memorandum" action="/chef/memorandum/list-by-fineDish?id=${id}"/>
	<jstl:if test="${status == 'proposed' }">
		<acme:submit code="authenticated.epicure.finedish.form.button.accepted" action="/chef/fine-dish/accepted"/>
		<acme:submit code="authenticated.epicure.finedish.form.button.denied" action="/chef/fine-dish/denied"/>	
	</jstl:if>
	
	
	<acme:button code="chef.memorandum.form.button.create" action="/chef/memorandum/create?id=${id}"/>
	
	
</acme:form>