<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="chef.memorandum.list.label.seqNumber" path="seqNumber" width="20%"/>
	<acme:list-column code="chef.memorandum.list.label.instantiationMoment" path="instantiationMoment" width="20%"/>
	<acme:list-column code="chef.memorandum.list.label.report" path="report" width="20%"/>
	<acme:list-column code="chef.memorandum.list.label.link" path="link" width="20%"/>
	<acme:list-column code="chef.memorandum.list.label.fineDish.identify.code" path="fineDish.code" width="20%"/>


	
</acme:list>