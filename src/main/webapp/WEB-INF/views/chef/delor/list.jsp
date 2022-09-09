<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ page import="acme.entities.cookingItem.Status" %>


<acme:list>

	<acme:list-column code="chef.pimpam.list.label.code" path="keylet" width="20%"/>
	<acme:list-column code="chef.pimpam.list.label.instationMoment" path="instationMoment" width="20%"/>
	<acme:list-column code="chef.pimpam.list.label.title" path="subject" width="20%"/>
	<acme:list-column code="chef.pimpam.list.label.description" path="explanation" width="20%"/>
	<acme:list-column code="chef.pimpam.list.label.budget" path="income" width="20%"/>	
	<acme:list-column code="chef.pimpam.list.label.startsAt" path="startsAt" width="20%"/>
	<acme:list-column code="chef.pimpam.list.label.finishesAt" path="finishesAt" width="20%"/>	
	<acme:list-column code="chef.pimpam.list.label.link" path="moreInfo" width="20%"/>


</acme:list>

<jstl:if test="${statusci == 'NONE_PUBLISHED'}">
	
		<acme:button code="chef.pimpam.form.button.create" action="/chef/delor/create?id=${param.id}"/>
		
		</jstl:if>

		
		


		
