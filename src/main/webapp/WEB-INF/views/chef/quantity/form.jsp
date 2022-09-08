<%@page language="java"%>
<%@ page import="acme.enums.Amount" %>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>


<acme:form>
		
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command, 'show, update')}">
			<acme:input-integer code="chef.quantity.form.label.number" path="number" readonly="${recipe.status == 'PUBLISHED'}" />
			
			<jstl:if test="${recipe.status == 'NONE_PUBLISHED'}" >
				<acme:submit code="chef.quantity.form.button.update" action="/chef/quantity/update"/>
			</jstl:if>
			
			<jstl:if test="${cookingitem.type == 'INGREDIENT'}" >
				<acme:input-textbox code="chef.quantity.form.label.amount" path="amount" readonly="true"/>
			</jstl:if>
			
			<acme:input-textbox code="chef.quantity.form.label.name" path="cookingitem.name" readonly="true"/>
			<acme:input-textbox code="chef.quantity.form.label.code" path="cookingitem.code" readonly="true"/>
			<acme:input-textarea code="chef.quantity.form.label.description" path="cookingitem.description" readonly="true"/>
			<acme:input-money code="chef.quantity.form.label.retailPrice" path="cookingitem.retailPrice" readonly="true"/>
			<acme:input-url code="chef.quantity.form.label.link" path="cookingitem.link" readonly="true"/>
			<acme:input-textbox code="chef.quantity.form.label.type" path="cookingitem.type" readonly="true"/>
		
			<jstl:if test="${cookingitem.status == 'NONE_PUBLISHED' && cookingitem.getChef().getId() == chefId}" >
				<acme:button code="chef.quantity.form.button.updateItem" action="/chef/cooking-item/update?id=${cookingitem.getId()}"/>
			</jstl:if>
			
		</jstl:when>	
		
		<jstl:when test="${command == 'createKitchenUtensil'}">
			<acme:input-integer code="chef.quantity.form.label.number" path="number"/>
			<acme:input-select code="chef.quantity.form.label.item" path="cookingitem">
			
				<jstl:forEach items="${cookingitems}" var = "i">
					<acme:input-option code="${i.getName()}" value="${i.getId()}" selected="${ i.getId() == cookingitem }"/>
				</jstl:forEach>
			</acme:input-select>
				
			<acme:submit code = "chef.quantity.form.button.create" action = "/chef/quantity/createKitchenUtensil?id=${param.id}"/>
			
		</jstl:when>
		
				<jstl:when test="${command == 'createIngredient'}">
			<acme:input-integer code="chef.quantity.form.label.number" path="number"/>
			

			<acme:input-select code="chef.quantity.form.label.item" path="cookingitem">
			
				<jstl:forEach items="${cookingitems}" var = "i">
					<acme:input-option code="${i.getName()}" value="${i.getId()}" selected="${ i.getId() == cookingitem }"/>
				</jstl:forEach>
			</acme:input-select>
			
				
			<acme:input-select code="chef.quantity.form.label.amount" path="amount">
				<acme:input-option code="chef.quantity.form.label.vacio" value=" " selected="${ ''}" />
				<acme:input-option code="chef.quantity.form.label.kilo" value="kilo" selected="${ amount == 'kilo' }"/>
				<acme:input-option code="chef.quantity.form.label.cm3" value="cm3" selected="${ amount == 'cm3' }"/>
				<acme:input-option code="chef.quantity.form.label.gram" value="gram" selected="${ amount == 'gram' }"/>
				<acme:input-option code="chef.quantity.form.label.gallon" value="gallon" selected="${ amount == 'gallon' }"/>
				<acme:input-option code="chef.quantity.form.label.spoon" value="spoon" selected="${ amount == 'spoon' }"/>
			
			</acme:input-select >
			
		
			

			<acme:submit code = "chef.quantity.form.button.create" action = "/chef/quantity/createIngredient?id=${param.id}"/>
			
		</jstl:when>
		
	</jstl:choose>
		
		
</acme:form>