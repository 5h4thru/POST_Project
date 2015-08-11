<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cancel Page</title>
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
	</h5><br>
	
	<h4>Appointment cancelled successfully and 
	the payment has been refunded after deduction of 10% of total amount</h4>
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