<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="${readonly}">
	<acme:input-textbox code="authenticated.configuration.list.label.defaultCurr" path="defaultCurr"/>		
	<acme:input-textbox code="authenticated.configuration.list.label.acceptedCurr" path="acceptedCurr"/>		
	
</acme:form>