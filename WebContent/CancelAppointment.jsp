<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel Appointment</title>
<link href="style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<f:view>
<center>
	<img src="hospitalheader.jpg" width="80%" style="height:100"></img>
	<c:choose>
	<c:when test="${srole=='PA'}">
	<div id="nav-menu" style="width:100%;">
		<h:panelGrid columns="5" width="100%">
		<h:outputLink value="ViewProfile.jsp">View Profile </h:outputLink>
		<h:outputLink value="MakeAppointment.jsp">Make Appointment </h:outputLink>
		<h:outputLink value="CancelAppointment.jsp">Cancel Appointment </h:outputLink>
		<h:outputLink value="SearchDoctor.jsp">Search Doctor </h:outputLink>
		<h:outputLink value="Home.jsp">Home</h:outputLink>
	</h:panelGrid>	
	</div>
	
	<br><h5 style="text-align:left;font-weight:normal;">
	<h:outputText value="Logged in as #{patientAppointmentBean.patientId}"></h:outputText>
	</h5>
	
	<h2> Cancel Appointment</h2><br>
	<h:form>
	<c:if test="${not empty patientAppointmentBean.scheduleList}">
	<h:outputText value="Your Appointments are shown below"></h:outputText>
	<h:dataTable border="1" value="#{patientAppointmentBean.scheduleList}" var="row" >
		<h:column id="regNo">
			<f:facet name="header">
				<h:outputText value="Registration No" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.registrationNo}"></h:outputText>
		</h:column>
		
		<h:column id="docId">
			<f:facet name="header">
				<h:outputText value="Doctor Id" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.doctorId}"></h:outputText>
		</h:column>
		
		<h:column id="date">
			<f:facet name="header">
				<h:outputText value="Date of Appointment" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.dateOfAdmission}">
				<f:convertDateTime pattern="dd-MMM-yyyy"/>
			</h:outputText>
		</h:column>
		
		<h:column id="slot">
			<f:facet name="header">
				<h:outputText value="Slot" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.slot}"></h:outputText>
		</h:column>
		
		<h:column id="cancel">
			<f:facet name="header">
				<h:outputText value="Cancel Appointment" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:selectBooleanCheckbox value="#{row.selected}"></h:selectBooleanCheckbox>
		</h:column>
	</h:dataTable>
	<br>
	<h:commandButton value="Cancel" action="#{patientAppointmentBean.cancelAppointment}"></h:commandButton>
	</c:if>
	</h:form>
	<br><h:outputText value="#{patientAppointmentBean.message}"></h:outputText>

</c:when>
		<c:otherwise>
		<center>
		<h2><h:outputText value="Access Denied. Login to continue"></h:outputText></h2><br><br>
		<h:outputLink value="Login.jsp">Login</h:outputLink>
		</center>
		</c:otherwise>
</c:choose>
</center>
</f:view>
</body>
</html>