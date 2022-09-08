<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>

	<acme:list-column code="chef.cookingItem.list.label.name" path="name" width="20%"/>
	<acme:list-column code="chef.cookingItem.list.label.code" path="code" width="20%"/>
	<acme:list-column code="chef.cookingItem.list.label.description" path="description" width="20%"/>
	<acme:list-column code="chef.cookingItem.list.label.retailPrice" path="retailPrice" width="20%"/>		
	<acme:list-column code="chef.cookingItem.list.label.link" path="link" width="20%"/>


</acme:list>

	<jstl:if test="${command == 'list-ingredient' }">
			<acme:button code="chef.cookingItem.list.button.createIngredient" action="/chef/cooking-item/createIngredient"/>
		
			</jstl:if>
			
			<jstl:if test="${command == 'list-kitchenUtensil' }">
			<acme:button code="chef.cookingItem.list.button.createKitchenUtensil" action="/chef/cooking-item/createKitchenUtensil"/>
		
			</jstl:if>


		
