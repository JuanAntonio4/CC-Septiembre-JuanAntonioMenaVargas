
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

 

<acme:form>
<jstl:choose>
    <jstl:when test="${command == 'show'}">
			<acme:input-textbox readonly="true" code="authenticated.epicure.finedish.form.label.status" path="status"/>
			<acme:input-textbox readonly="true" code="authenticated.epicure.finedish.form.label.code" path="code"/>	
			<acme:input-textbox readonly="true" code="authenticated.epicure.finedish.form.label.request" path="request"/>	
			<acme:input-money readonly="true" code="authenticated.epicure.finedish.form.label.budget" path="budget"/>
			<acme:input-money code="epicure.label.moneyExchange" path="moneyExchange" readonly="true" />
			<acme:input-moment readonly="true" code="authenticated.epicure.finedish.form.label.startsAt" path="startsAt"/>
			<acme:input-moment readonly="true" code="authenticated.epicure.finedish.form.label.finishesAt" path="finishesAt"/>
			<acme:input-url readonly="true" code="authenticated.epicure.finedish.form.label.link" path="link"/>	
			<acme:input-textarea readonly="true" code="authenticated.epicure.finedish.form.label.chef.organisation" path="chef.organisation"/>
			<acme:input-textarea readonly="true" code="authenticated.epicure.finedish.form.label.chef.assertion" path="chef.assertion"/>
			<acme:input-url readonly="true" code="authenticated.epicure.finedish.form.label.chef.link" path="chef.link"/>

			
			
			<jstl:if test="${publishedStatus == 'NONE_PUBLISHED' }">
				<acme:button code="authenticated.epicure.finedish.form.button.update" action="/epicure/fine-dish/update?id=${id}"/>
				<acme:submit code="authenticated.epicure.finedish.form.button.delete" action="/epicure/fine-dish/delete?id=${id}"/>
				<acme:submit code="authenticated.epicure.finedish.form.button.publish" action="/epicure/fine-dish/publish"/>
			</jstl:if>
			
			<acme:button code="epicure.finedish.form.buttom.memorandums" action="/epicure/memorandum/list-by-fineDish?id=${id}"/>
			
			<acme:button code="epicure.memorandum.form.button.create" action="/epicure/memorandum/create?id=${id}"/>
			
		</jstl:when>
		<jstl:when test="${command == 'create'}">
		
			<acme:input-textbox  code="authenticated.epicure.finedish.form.label.code" path="code" placeholder="AA:AAA-000"/>	
			<acme:input-textbox  code="authenticated.epicure.finedish.form.label.request" path="request"/>	
			<acme:input-money  code="authenticated.epicure.finedish.form.label.budget" path="budget"/>	
			<acme:input-moment  code="authenticated.epicure.finedish.form.label.startsAt" path="startsAt" placeholder="0000/00/00 00:00"/>
			<acme:input-moment  code="authenticated.epicure.finedish.form.label.finishesAt" path="finishesAt" placeholder="0000/00/00 00:00"/>
			<acme:input-url  code="authenticated.epicure.finedish.form.label.link" path="link"/>	
			<acme:input-select code="authenticated.epicure.finedish.form.label.chef" path="chefUN">
			
	   			<jstl:forEach items="${chefs}" var="chef">
					<acme:input-option code="${chef.getUserAccount().getUsername()}" value="${chef.getUserAccount().getUsername()}" selected="${ chef.getUserAccount().getUsername() == chefUN }"/>
				</jstl:forEach>
			</acme:input-select>
			
			<acme:submit code="authenticated.epicure.finedish.form.button.create" action="/epicure/fine-dish/create"/>
		</jstl:when>	
		
		<jstl:when test="${command == 'update'}">
		
		
			<acme:input-textbox code="authenticated.epicure.finedish.form.label.code" path="code"/>	
			<acme:input-textbox code="authenticated.epicure.finedish.form.label.request" path="request"/>	
			<acme:input-money code="authenticated.epicure.finedish.form.label.budget" path="budget"/>	
			<acme:input-moment code="authenticated.epicure.finedish.form.label.startsAt" path="startsAt"/>
			<acme:input-moment code="authenticated.epicure.finedish.form.label.finishesAt" path="finishesAt"/>
			<acme:input-url code="authenticated.epicure.finedish.form.label.link" path="link"/>
			<acme:input-select code="authenticated.epicure.finedish.form.label.chef" path="chefUN">
			
	   			<jstl:forEach items="${chefs}" var="chef">
					<acme:input-option code="${chef.getUserAccount().getUsername()}" value="${chef.getUserAccount().getUsername()}" selected="${ chef.getUserAccount().getUsername() == chefUN }"/>
				</jstl:forEach>
			</acme:input-select>
			
			<acme:submit code="authenticated.epicure.finedish.form.button.update" action="/epicure/fine-dish/update"/>
			
		</jstl:when>
					
	</jstl:choose>	

</acme:form>