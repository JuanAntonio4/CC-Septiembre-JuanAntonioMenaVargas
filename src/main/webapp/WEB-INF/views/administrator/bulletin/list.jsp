<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list readonly="true">
	<acme:list-column code="administrator.bulletin.list.label.heading" path="heading" width="20%"/>
	<acme:list-column code="administrator.bulletin.list.label.instationMoment" path="instationMoment" width="20%"/>		
	<acme:list-column code="administrator.bulletin.list.label.pieceOfText" path="pieceOfText" width="20%"/>
	<acme:list-column code="administrator.bulletin.list.label.critic" path="critic" width="20%"/>				
	<acme:list-column code="administrator.bulletin.list.label.link" path="link" width="20%"/>
					
</acme:list>

<acme:button code="administrator.bulletin.form.button.create" action="/administrator/bulletin/create"/>
