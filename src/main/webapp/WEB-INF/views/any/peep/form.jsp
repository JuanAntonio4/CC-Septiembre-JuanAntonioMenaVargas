<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>

	<acme:input-textbox code="any.peep.form.label.heading" path="heading"/>
	<acme:input-textbox code="any.peep.form.label.writer" path="writer"/>
	<acme:input-textbox code="any.peep.form.label.pieceOfText" path="pieceOfText"/>
	<acme:input-textbox code="any.peep.form.label.email" path="email"/>

	<jstl:if test="${command == 'create'}">

		<acme:input-checkbox code="any.peep.form.label.confirm" path="confirm"/>

		<acme:submit code="any.peep.form.button.create" action="/any/peep/create"/>
	</jstl:if>

</acme:form>