<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Appointment</title>
<script type="text/javascript" src="datetimepicker.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<center>
<img alt="" src="hospitalheader.jpg"><br>
</center>

<br>
<br>
<f:view>
<c:choose>
<c:when test="${srole=='DC'}">
<center>
<table cellpadding="20" id="nav-menu">
<tr>
<td><h:outputLink value="UpdateDoctorProfile.jsp">Update Profile</h:outputLink></td>
<td><h:outputLink value="ViewAppointments.jsp">My Appointments</h:outputLink></td>
<td><h:outputLink value="ScheduleSurgery.jsp">Schedule Surgery</h:outputLink></td>
<td><h:outputLink value="DrugRequestGenerate.jsp">Medicine Request</h:outputLink></td>
<td><h:outputLink value="ApplyForLeave.jsp">Apply Leave</h:outputLink></td>
<td><h:outputLink value="HomePage.jsp">Home</h:outputLink></td>
</tr>
</table>
</center>
<c:set var="suser" value="DC102" scope="session"></c:set>
Logged in as&nbsp;<c:out value="${suser}"></c:out>
<center>
<h:form id="AddDocForm">
<h3>Appointments for the 'Day'</h3><BR><BR><BR>
<h:panelGrid columns="3">
<h:outputText value="Please enter the date"></h:outputText>
<h:panelGroup>
<h:inputText id="doj" value="#{appointmentBean.date}" required="true"requiredMessage="Please Select the Date">
<f:convertDateTime type="date" pattern="dd-MMM-yyyy" />
</h:inputText>
<a href="javascript:NewCal('AddDocForm:doj','ddmmmyyyy')"><img src="cal.gif" width="20" height="20" border="0" alt="Pick a Date"></a>
</h:panelGroup>
<h:message for="doj"></h:message>
</h:panelGrid><BR>
<h:commandButton value="view appointment" action="#{appointmentBean.viewAppointment}"></h:commandButton>


<c:if test="${not empty appointmentBean.ato}">
<h:dataTable border="1" value="#{appointmentBean.ato}"  var="row">
<h:column>
<f:facet name="header">
<h:outputText value="PatientId"></h:outputText>
</f:facet>
<h:outputText value="#{row.patientId}"></h:outputText>
</h:column>
<h:column>
<f:facet name="header">
<h:outputText value="Reason of Appointment"></h:outputText>
</f:facet>
<h:outputText value="#{row.reasonOfAppointment}"></h:outputText>
</h:column>
<h:column>
<f:facet name="header">
<h:outputText value="Slot"></h:outputText>
</f:facet>
<h:outputText value="#{row.slot}"></h:outputText>
</h:column>
</h:dataTable>
</c:if>
<br>
<br>
<h:outputText value="#{appointmentBean.message}"></h:outputText>
</h:form>
</center>
</c:when>
		<c:otherwise>
		<center>
		<h2><h:outputText value="Access Denied. Login to continue"></h:outputText></h2><br><br>
		<h:outputLink value="Login.jsp">Login</h:outputLink>
		</center>
		</c:otherwise>
</c:choose>
</f:view>
</body>
</html>