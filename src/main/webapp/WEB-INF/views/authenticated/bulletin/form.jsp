<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
	<acme:input-textbox code="authenticated.bulletin.form.label.heading" path="heading" readonly="true"/>
	<acme:input-textbox code="authenticated.bulletin.form.label.instationMoment" path="instationMoment" readonly="true"/>
	<acme:input-textbox code="authenticated.bulletin.form.label.pieceOfText" path="pieceOfText" readonly="true"/>
	<acme:input-textbox code="authenticated.bulletin.form.label.critic" path="critic" readonly="true"/>
	<acme:input-url code="authenticated.bulletin.form.label.link" path="link"/>
	</acme:form>