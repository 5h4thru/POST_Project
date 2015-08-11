<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Update Doctor Profile</title>
</head>
<body>
<f:view>
<center>
<img src="hospitalheader.jpg" width="700"/></center>
<c:choose>
<c:when test="${srole=='DC'}">
<center>
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
</center>
Logged in as <c:out value="${uname}"></c:out> 
<center>
<h:form>
<h2>Edit Your Profile</h2><br>
Your details are below.To edit,make changes in the field and click on the Update button<br>
	<h:panelGrid border="1" columns="2">
		<h:outputText value="Doctor ID"></h:outputText>
		<c:out value="${uname}"></c:out>
		<h:outputText value="First name"></h:outputText>
		<h:inputText value="#{doctorBean.employeeName}" required="true" requiredMessage="First name is Mandatory">
		<f:validator validatorId="fnv"/>
		</h:inputText>
		<h:outputText value="Department"></h:outputText>
		<h:inputText value="#{doctorBean.department}" required="true" requiredMessage="Department is Mandatory">
		<f:validator validatorId="sv"/>
		</h:inputText>
		<h:outputText value="Qualification"></h:outputText>
		<h:inputText value="#{doctorBean.qualification}" required="true" requiredMessage="Qualification is Mandatory">
		<f:validator validatorId="sv"/>
		</h:inputText>
		<h:outputText value="Consultation Fees"></h:outputText>
		<h:inputText value="#{doctorBean.consultationFees}" required="true" requiredMessage="Consultation Fees is Mandatory" converterMessage="consultation fees accept only numbers" validatorMessage="Fees should be less than 10000">
		<f:validateDoubleRange minimum="1" maximum="9999"/>
		</h:inputText>
		<h:outputText value="Address"></h:outputText>
		<h:inputTextarea value="#{doctorBean.address}" required="true" requiredMessage="Address is Mandatory" validatorMessage="Address cannot exceed more than 80 characters">
		<f:validateLength minimum="3" maximum="80"/>
		</h:inputTextarea>
		<h:outputText value="Phone No."></h:outputText>
		<h:inputText value="#{doctorBean.phoneNo}" required="true" requiredMessage="Phone No. is Mandatory" converterMessage="Phone No. accept only numbers" validatorMessage="Phone No.should be 10 digits">
		<f:validateLength maximum="10" minimum="10"></f:validateLength>
		</h:inputText>
	</h:panelGrid>
	<h:commandButton value="Update Profile" action="#{doctorBean.updateDoctorDetails}"></h:commandButton>
	<h:outputText value="#{doctorBean.message}"></h:outputText><br>
	<h:messages></h:messages>
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