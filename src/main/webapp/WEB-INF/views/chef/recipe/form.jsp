
  
<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<jstl:choose>

	<jstl:when test="${command == 'show' }">
	
		<acme:input-textbox code="chef.recipe.form.label.code" path="code" readonly="true"/>
		<acme:input-textbox code="chef.recipe.form.label.heading" path="heading" readonly="true"/>
		<acme:input-textbox code="chef.recipe.form.label.description" path="description" readonly="true"/>
		<acme:input-textbox code="chef.recipe.form.label.preparationNotes" path="preparationNotes" readonly="true"/>
		<acme:input-url code="chef.recipe.form.label.link" path="link" readonly="true"/>

	
	<jstl:if test="${status == 'PUBLISHED' }">
		<acme:button code="chef.recipe.form.buttom.items" action="/chef/quantity/list-by-recipe?id=${id}"/>
	</jstl:if>
	
	<jstl:if test="${status == 'NONE_PUBLISHED' }">
		<acme:button code="chef.recipe.form.buttom.items" action="/chef/quantity/list-by-recipe?id=${id}"/>
		<acme:button code="chef.recipe.form.button.update" action="/chef/recipe/update?id=${id}"/>
		<acme:submit code="chef.recipe.form.button.delete" action="/chef/recipe/delete"/>
		<acme:submit code="chef.recipe.form.button.publish" action="/chef/recipe/publish"/>
	</jstl:if>
	
	</jstl:when>

		
	<jstl:when test = "${command == 'create' }">
		<acme:input-textbox code="chef.recipe.form.label.code" path="code" readonly="true" />
		<acme:input-textbox code="chef.recipe.form.label.heading" path="heading"/>
		<acme:input-textbox code="chef.recipe.form.label.description" path="description"/>
		<acme:input-textbox code="chef.recipe.form.label.preparationNotes" path="preparationNotes"/>
		<acme:input-url code="chef.recipe.form.label.link" path="link"/>
		<acme:submit code = "chef.recipe.form.button.create" action = "/chef/recipe/create"/>
	</jstl:when>
	
	<jstl:when test = "${command == 'update' }">
		<acme:input-textbox code="chef.recipe.form.label.code" path="code" readonly="true"/>
		<acme:input-textbox code="chef.recipe.form.label.heading" path="heading"/>
		<acme:input-textbox code="chef.recipe.form.label.description" path="description"/>
		<acme:input-textbox code="chef.recipe.form.label.preparationNotes" path="preparationNotes"/>
		<acme:input-url code="chef.recipe.form.label.link" path="link"/>
		<acme:submit code="chef.recipe.form.button.update" action="/chef/recipe/update"/>
	</jstl:when>

	
	</jstl:choose>


</acme:form>