<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Doctor</title>
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
	<h:outputText value="Logged in as #{searchDoctorBean.userName}"></h:outputText>
	</h5>
	
	<h2> Search Doctor</h2><br>
	<h:form>
	<h:panelGrid border="1" columns="2">
		<h:outputText value="Doctor Name"></h:outputText>
		<h:selectOneMenu value="#{searchDoctorBean.doctorId}">
			<f:selectItem itemLabel="Select......"/>
			<f:selectItems value="#{oPDRegistrationBean.doctors}"/>
		</h:selectOneMenu>
	
		<h:outputText value="Specialization"></h:outputText>
		<h:selectOneMenu id="specialization" value="#{searchDoctorBean.department}">
			<f:selectItem itemLabel="Select......"/>
			<f:selectItems value="#{searchDoctorBean.listOfDepartment}"/>
		</h:selectOneMenu>
		
		<h:outputText value="Availability of slots"></h:outputText>	
		<h:selectOneRadio id="slot" value="#{searchDoctorBean.slot}">
			<f:selectItem itemLabel="9:AM-12:PM" itemValue="S1"/>
			<f:selectItem itemLabel="12:PM-3:PM" itemValue="S2"/>
			<f:selectItem itemLabel="3:PM-6:PM" itemValue="S3"/>
		</h:selectOneRadio>
	</h:panelGrid>
	<h:commandButton value="Search" action="#{searchDoctorBean.getDoctor}"></h:commandButton>
	</h:form>
	<h:message for="specialization"></h:message><br>
	
	<c:if test="${not empty searchDoctorBean.details}">
	<h:dataTable border="1" value="#{searchDoctorBean.details}" var="row">
		<h:column id="docId">
			<f:facet name="header">
				<h:outputText value="Doctor Id" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.userName}"></h:outputText>
		</h:column>
		
		<h:column id="fees">
			<f:facet name="header">
				<h:outputText value="Consultation fees" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.consultationFees}"></h:outputText>
		</h:column>
		
		<h:column id="slot1">
			<f:facet name="header">
				<h:outputText value="9:AM-12:PM" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.slot1}"></h:outputText>
		</h:column>
		
		<h:column id="slot2">
			<f:facet name="header">
				<h:outputText value="12:PM-3:PM" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.slot2}"></h:outputText>
		</h:column>
		
		<h:column id="slot3">
			<f:facet name="header">
				<h:outputText value="3:PM-6:PM" styleClass="dataTableHeader"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.slot3}"></h:outputText>
		</h:column>
	</h:dataTable>
	<div style="font-size:12px;font-weight:bold;">
	<h:outputText value="NA:Not Available "></h:outputText>
	<h:outputText value=" AV:Available"></h:outputText>
	</div>
	</c:if>
	<h:outputText value="#{searchDoctorBean.message}"></h:outputText><br>
	<h:outputText value="#{oPDRegistrationBean.message}"></h:outputText>
	
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