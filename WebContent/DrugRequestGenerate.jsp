<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link href="style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Drug Request Generate</title>
</head>
<body>
<center>
<img src="hospitalheader.jpg" />
<f:view>
<c:choose>
<c:when test="${srole=='DC'}">
<table cellpadding="20" id=nav-menu>
<tr>
<td><h:outputLink value="UpdateDoctorProfile.jsp">Update Profile</h:outputLink></td>
<td><h:outputLink value="ViewAppointment.jsp">My Appointments</h:outputLink></td>
<td><h:outputLink value="ScheduleSurgery.jsp">Schedule Surgery</h:outputLink></td>
<td><h:outputLink value="DrugRequestGenerate.jsp">Medicine Request</h:outputLink></td>
<td><h:outputLink value="ApplyForLeave.jsp">Apply Leave</h:outputLink></td>
<td><h:outputLink value="Home.jsp">Home</h:outputLink></td>
</tr>
</table>
<br><br><br>
Logged in as <c:out value="${uname}"></c:out>
<h:form>
	<h3>Place Request For Medicine</h3><br><br><br>
		<h:panelGrid border="1" columns="2">
			<h:outputText value="Medicine Name"></h:outputText>
			<h:inputText id="medicine" value="#{doctorBean.drugName}" required="true" requiredMessage="Medicine Required!!">
				<f:validator validatorId="nv"/>
			</h:inputText>
			<h:outputText value="Company/Manufacturer"></h:outputText>
			<h:inputText id="company" value="#{doctorBean.manufacturer}" required="true" requiredMessage="Company/Manufacturer Required!!">
				<f:validator validatorId="nv"/>
			</h:inputText>
			<h:outputText value="Quantity"></h:outputText>
			<h:inputText id="quant" value="#{doctorBean.quantity}"  required="true" requiredMessage="Quantity Required!!" converterMessage="Please Enter a Valid Number!!" validatorMessage="Quantity cannot be less than 1!!" >
				<f:validateLongRange minimum="1"></f:validateLongRange>
			</h:inputText>
			</h:panelGrid>
			<h:selectOneRadio id="urgency" value="#{doctorBean.urgent}" required="true" requiredMessage="Urgency Selection Required!!">
			<f:selectItem itemLabel="Urgent" itemValue="UG"/>
			<f:selectItem itemLabel="Not urgent" itemValue="NU"/>
		</h:selectOneRadio>
		<br><br>
		<h:commandButton  value="Request" action="#{doctorBean.requestMedicine}"></h:commandButton>
	</h:form>
	<br>
	<br>
	<h:outputText value="#{doctorBean.message}"></h:outputText>
	<h:message for="medicine"></h:message><br>
	<h:message for="company"></h:message><br>
	<h:message for="quant"></h:message><br>
	<h:message for="urgency"></h:message><br>
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