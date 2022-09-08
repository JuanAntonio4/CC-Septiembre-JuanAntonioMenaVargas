<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
<acme:input-textbox code="any.recipe.form.label.code" path="code" readonly="true"/>
	<acme:input-textbox code="any.recipe.form.label.heading" path="heading" readonly="true"/>
	<acme:input-textbox code="any.recipe.form.label.description" path="description" readonly="true"/>
	<acme:input-textbox code="any.recipe.form.label.preparationNotes" path="preparationNotes" readonly="true"/>
	<acme:input-url code="any.recipe.form.label.link" path="link"/>
	<acme:button code="any.recipe.form.buttom.cookingitems" action="/any/cooking-item/list-by-recipe?id=${id}"/> 
	
</acme:form>