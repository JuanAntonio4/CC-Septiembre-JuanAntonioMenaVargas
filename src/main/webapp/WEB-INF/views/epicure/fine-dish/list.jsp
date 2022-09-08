

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.epicure.finedish.list.label.status" path="status" width="20%"/>
	<acme:list-column code="authenticated.epicure.finedish.list.label.code" path="code" width="20%"/>
	<acme:list-column code="authenticated.epicure.finedish.list.label.budget" path="budget" width="20%"/>

</acme:list>
<acme:button code="authenticated.epicure.finedish.form.button.create" action="/epicure/fine-dish/create"/>
