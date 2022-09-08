<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<jstl:choose>
		<jstl:when test="${command=='show'}">
			
		
				<acme:input-textbox code="chef.cookingItem.form.label.name" path="name" readonly="true" />
				<acme:input-textbox code="chef.cookingItem.form.label.code" path="code" readonly="true" />
				<acme:input-textbox code="chef.cookingItem.form.label.description" path="description" readonly="true" />
				<acme:input-money code="chef.cookingItem.form.label.retailPrice" path="retailPrice" readonly="true" />
				<acme:input-money code="epicure.label.moneyExchange" path="moneyExchange" readonly="true" />
				<acme:input-url code="chef.cookingItem.form.label.link" path="link" readonly="true" />
				<acme:button code="chef.pimpam.form.button.list" action="/chef/pimpam/list-pimpam?id=${id}"/>
	
			
			<jstl:if test="${status == 'NONE_PUBLISHED' }">
				<acme:submit code="chef.cookingItem.form.button.delete" action="/chef/cooking-item/delete"/>
				<acme:button code="chef.cookingItem.form.button.update" action="/chef/cooking-item/update?id=${id}"/>	
				<acme:submit code="chef.cookingItem.form.button.publish" action="/chef/cooking-item/publish"/>	
			</jstl:if>
			
		</jstl:when>

			<jstl:when test="${command == 'createIngredient'}">
			<acme:input-textbox code="chef.cookingItem.form.label.name" path="name"/>
			<acme:input-textbox code="chef.cookingItem.form.label.code" path="code" readonly="true"  />
			<acme:input-textbox code="chef.cookingItem.form.label.description" path="description"/>
			<acme:input-money code="chef.cookingItem.form.label.retailPrice" path="retailPrice" />
			<acme:input-textbox code="chef.cookingItem.form.label.link" path="link"/>
			<acme:submit code="chef.cookingItem.form.button.createIngredient" action="/chef/cooking-item/createIngredient"/>
		</jstl:when>
		
		<jstl:when test="${command == 'createKitchenUtensil'}">
			<acme:input-textbox code="chef.cookingItem.form.label.name" path="name"/>
			<acme:input-textbox code="chef.cookingItem.form.label.code" path="code" readonly="true"  />
			<acme:input-textbox code="chef.cookingItem.form.label.description" path="description"/>
			<acme:input-money code="chef.cookingItem.form.label.retailPrice" path="retailPrice" />
			<acme:input-textbox code="chef.cookingItem.form.label.link" path="link"/>
			<acme:submit code="chef.cookingItem.form.button.createKitchenUtensil" action="/chef/cooking-item/createKitchenUtensil"/>
			
		</jstl:when>
		<jstl:when test="${command == 'update'}">
				<acme:input-textbox code="chef.cookingItem.form.label.name" path="name"/>
				<acme:input-textbox code="chef.cookingItem.form.label.code" path="code" readonly="true"  />
				<acme:input-textbox code="chef.cookingItem.form.label.description" path="description"/>
				<acme:input-money code="chef.cookingItem.form.label.retailPrice" path="retailPrice" />
				<acme:input-textbox code="chef.cookingItem.form.label.link" path="link"/>
				<acme:submit code="chef.cookingItem.form.button.update" action="/chef/cooking-item/update"/>	
		</jstl:when>
			



	</jstl:choose>

</acme:form>