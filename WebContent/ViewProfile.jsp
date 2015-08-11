<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Profile</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>

<center id="header"></center> 
<center>
<f:view>
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
<h:form>
<h2 align="center"> My Profile</h2><br>
<h5 align="left"> Logged in as <h:outputText value="#{patientBean.patientId}"></h:outputText></h5><br>
<h:panelGrid border="1" columns="2">
			<h:outputText value="First Name"></h:outputText>
			<h:outputText value="#{patientBean.firstName}"></h:outputText>
			<h:outputText value="Last Name"></h:outputText>
			<h:outputText value="#{patientBean.lastName}"></h:outputText>
			<h:outputText value="Contact Person"></h:outputText>
			<h:outputText value="#{patientBean.contactPerson}"></h:outputText>
			<h:outputText value="Address"></h:outputText>
			<h:outputText value="#{patientBean.address}"></h:outputText>
			<h:outputText value="PhoneNo"></h:outputText>
			<h:outputText value="#{patientBean.phoneNo}"></h:outputText>
			
		</h:panelGrid><br>
<h:commandButton value="View Payments" action="#{patientBean.viewPayments}"></h:commandButton><br><br>
<h:outputText value="#{patientBean.message}"></h:outputText>
</h:form>
</c:when>
		<c:otherwise>
		<center>
		<h2><h:outputText value="Access Denied. Login to continue"></h:outputText></h2><br><br>
		<h:outputLink value="Login.jsp">Login</h:outputLink>
		</center>
		</c:otherwise>
</c:choose>
</f:view>
</center>
</body>
</html>