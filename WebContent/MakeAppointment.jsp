<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Make Appointment</title>
<script type="text/javascript" src="datetimepicker.js"></script>
<script type="text/javascript">
	function pickDate()
	{
		new NewCal('form1:date','ddmmmyyyy');
	}
</script>
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
	<h:outputText value="Logged in as #{patientBean.patientId}"></h:outputText>
	</h5>
	
	<h2> Make Appointment</h2><br>
	<h:form id="form1">
	<h:panelGrid border="1" columns="3">
		<h:outputText value="Doctor Id"></h:outputText>
		<h:panelGroup>
			<h:inputText id="doctorId" value="#{patientBean.doctorId}" required="true" requiredMessage="Doctor Id required!!!"></h:inputText>
			<h:outputLink value="SearchDoctor.jsp"><h6>Search Doctor</h6></h:outputLink>
		</h:panelGroup>
		<h:message for="doctorId"></h:message>
		
		<h:outputText value="Date of Appointment"></h:outputText>
		<h:panelGroup>
			<h:inputText id="date" value="#{patientBean.date}" required="true" requiredMessage="Date required!!!" 
			converterMessage="Invalid format, it should be in (dd-MMM-yyyy) format">
				<f:convertDateTime pattern="dd-MMM-yyyy"/>
				<f:validator validatorId="dateValidator"/>
			</h:inputText>
			<a onclick="pickDate()"><img style="vertical-align:bottom;" src="cal.gif" border="0" alt="Pick a Date"></a>
		</h:panelGroup>
		<h:message for="date"></h:message>
		
		<h:outputText value="Slot of Appointment"></h:outputText>	
		<h:selectOneRadio id="slot" value="#{patientBean.slot}" required="true" 
		requiredMessage="Select slot for Appointment" style="font-size:5;">
			<f:selectItem itemLabel="9:AM-12:PM" itemValue="S1"/>
			<f:selectItem itemLabel="12:PM-3:PM" itemValue="S2"/>
			<f:selectItem itemLabel="3:PM-6:PM" itemValue="S3"/>
		</h:selectOneRadio>
		
		<h:message for="slot"></h:message>
	</h:panelGrid>
	<br><br><h:commandButton value="Check for Availability" action="#{patientBean.checkAppointment}"></h:commandButton>
	</h:form>
	
	<c:if test="${patientBean.consultationFees!=0}">
	<h:form>
		<h:panelGrid border="1" columns="3">
			<h:outputText value="Consultation Fees"></h:outputText>
			<h:outputText id="fees" value="#{patientBean.consultationFees}"></h:outputText>
			<h:message for="fees"></h:message>
			
			<h:outputText value="Reason for Appointment"></h:outputText>
			<h:inputText id="appointmentReason" value="#{patientBean.reasonForAppointment}" required="true"
			requiredMessage="Reason for Appointment is Mandatory">
				<f:validator validatorId="reason"/>
			</h:inputText>
			<h:message for="appointmentReason"></h:message>
		</h:panelGrid>
		<br><h:commandButton value="Make Payment" action="#{patientBean.makePayment}"></h:commandButton>
	</h:form>
	</c:if>
	<h:outputText value="#{patientBean.message}"></h:outputText>
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