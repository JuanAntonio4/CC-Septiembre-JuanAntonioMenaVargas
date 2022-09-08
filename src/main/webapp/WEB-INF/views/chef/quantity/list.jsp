<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>
<%@ page import="acme.entities.recipe.Status" %>
;
<acme:list>
	<acme:list-column code="chef.quantity.list.label.number" path="number" width="20%"/>
	<acme:list-column code="chef.quantity.list.label.name" path="cookingitem.name" width="20%"/>			
	<acme:list-column code="chef.quantity.list.label.code" path="cookingitem.code" width="20%"/>		
	<acme:list-column code="chef.quantity.list.label.retailPrice" path="cookingitem.retailPrice" width="20%"/>	
	<acme:list-column code="chef.quantity.list.label.type" path="cookingitem.type" width="20%"/>	

		
</acme:list>

		<jstl:if test="${statusreci == 'NONE_PUBLISHED'}">
		<jstl:if test="${lingre == '0'}">
		<h2><acme:message code="quantity.chef.noingredientes"/></h2>
		</jstl:if>
		
		<jstl:if test="${lingre != '0'}">
			<acme:button code="chef.recipe.form.button.quantity.ingredient" action="/chef/quantity/createIngredient?id=${param.id}"/>
		</jstl:if>
		
		<jstl:if test="${lutensil == '0'}">
			<h2><acme:message code="quantity.chef.noutensil"/></h2>
		</jstl:if>
		
		<jstl:if test="${lutensil != '0'}">
			<acme:button code="chef.recipe.form.button.quantity.utensil" action="/chef/quantity/createKitchenUtensil?id=${param.id}"/>
		</jstl:if>
		
		
		</jstl:if>



		