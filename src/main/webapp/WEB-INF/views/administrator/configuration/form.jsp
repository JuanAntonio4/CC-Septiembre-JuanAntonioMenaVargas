<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="administrator.configuration.form.label.SpamWords" path="spamWords"/>
	<acme:input-textbox code="administrator.configuration.form.label.SpamThreshold" path="spamThreshold" />
	<acme:input-textbox code="administrator.configuration.form.label.defaultCurr" path="defaultCurr" />
	<acme:input-textbox code="administrator.configuration.form.label.acceptedCurr" path="acceptedCurr" />

	<acme:submit code="administrator.configuration.form.button.update" action="/administrator/configuration/update"/>
	
</acme:form>
