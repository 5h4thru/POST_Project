<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="Javascript" type="text/javascript" src="datetimepicker.js"></script>
<link rel="Stylesheet" href="style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule Surgery</title>
</head>
<body>

<f:view>
<center><img src="hospitalheader.jpg"><br><br><br><br></center>
<c:choose>
<c:when test="${srole=='DC'}">
<center>
<table cellpadding="20" id=nav-menu width="100%">
<tr>
<td><h:outputLink value="UpdateDoctorProfile.jsp">Update Profile</h:outputLink></td>
<td><h:outputLink value="ViewAppointment.jsp">My Appointments</h:outputLink></td>
<td><h:outputLink value="ScheduleSurgery.jsp">Schedule Surgery</h:outputLink></td>
<td><h:outputLink value="DrugRequestGenerate.jsp">Medicine Request</h:outputLink></td>
<td><h:outputLink value="ApplyForLeave.jsp">Apply Leave</h:outputLink></td>
<td><h:outputLink value="Home.jsp">Home</h:outputLink></td>
</tr>
</table></center>
Logged in as <c:out value="${uname}">
		
	</c:out>
<center>
<h2>Schedule Surgery</h2><br><br><br>
<h:form id="form">
<h:panelGrid border="1" columns="2">

			<h:outputText value="Enter Patient Id"/>
			<h:inputText value="#{doctorBean.patientId}" required="true" requiredMessage="PatientId Required!!!"></h:inputText>
			
		<h:outputText value="Slot of Surgery"/>
			<h:selectOneRadio value="#{doctorBean.slot}" required="true" requiredMessage="Select Slot for Surgery">
				<f:selectItem itemLabel="9:AM-12:PM" itemValue="S1"></f:selectItem>
				<f:selectItem itemLabel="12:PM-3:PM" itemValue="S2"></f:selectItem>
				<f:selectItem itemLabel="3:PM-6:PM" itemValue="S3"></f:selectItem>
			</h:selectOneRadio>
			
		<h:outputText value="Date of Surgery"/>
			<h:panelGroup>
			<h:inputText id="date" value="#{doctorBean.dateOfSurgery}" required="true" requiredMessage="Select Date for Surgery" converterMessage="Date Should be in dd-MMM-yyyy">
			<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/>
			<f:validator validatorId="dsv"></f:validator>
			</h:inputText>
			<a href="javascript:NewCal('form:date','ddmmmyyyy')">
  			<img src="cal.gif" width="16" height="16" border="0" alt="Pick a Date"></a>
			</h:panelGroup>
			
			<h:outputText value="OT Number"/>
			<h:selectOneMenu value="#{doctorBean.otNo}" required="true" requiredMessage="Select OT number">
				<f:selectItem itemLabel="Select"/>
				<f:selectItems value="#{doctorBean.otList}"/>
			</h:selectOneMenu>
			
			<h:outputText value="Surgery Name"/>
			<h:inputText value="#{doctorBean.surgeryName}" required="true" requiredMessage="Enter Surgery Name">
				<f:validator validatorId="snv"></f:validator>
			</h:inputText>
			
			<h:commandButton value="Book OT" action="#{doctorBean.scheduleSurgery}"></h:commandButton>
			<h:commandButton value="Reset" type="reset"></h:commandButton>
			
		</h:panelGrid>
		<h:messages></h:messages>
		<h:outputText value="#{doctorBean.message}"></h:outputText>
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