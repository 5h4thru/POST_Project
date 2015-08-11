<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="datetimepicker.js"></script>

<link rel="stylesheet" href="style.css" style="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Apply For Leave</title>
</head>
<body>
<f:view>
	<center><img src="hospitalheader.jpg" width="700" /></center>
<c:choose>
<c:when test="${srole=='DC'}">
	<center>
	<table cellpadding="20" id="nav-menu">
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
	<center>
	<H2>Apply For Leave</H2>
	</center>
Logged in as <c:out value="${uname}"></c:out>


	<h:form id="form">

		<center><h:panelGrid border="1" columns="3">

			<h:outputText value="Type Of Leave"></h:outputText>
			<h:selectOneRadio id="type" value="#{employeeLeaveBean.leaveType}"
				required="true" requiredMessage="Please select the type of leave">
				<f:selectItem itemLabel="Casual Leave" itemValue="CL" />
				<f:selectItem itemLabel="Medical Leave" itemValue="ML" />
				<f:selectItem itemLabel="Loss Of Pay" itemValue="LP" />
			</h:selectOneRadio>
			<h:message for="type"></h:message>

			<h:outputText value="Emergency contact"></h:outputText>
			<h:inputText id="phone" value="#{employeeLeaveBean.phoneNo}"
				required="true" requiredMessage="Phone Number is mandatory">
				<f:validator validatorId="leavePhone"/>
			</h:inputText>
			<h:message for="phone"></h:message>

			<h:outputText value="From Date" />
			<h:panelGroup>
				<h:inputText id="fdate" value="#{employeeLeaveBean.fromDate}"
					required="true" requiredMessage="From Date is mandatory"
					converterMessage="Invalid Date Format">
					<f:convertDateTime pattern="dd-MMM-yyyy" />
				</h:inputText>
				<a onclick="javascript:new NewCal('form:fdate','ddmmmyyyy')">
				 <img src="cal.gif" width="16"
					height="16" border="0" alt="Pick a Date"></a>
			</h:panelGroup>
			<h:message for="fdate"></h:message>

			<h:outputText value="To Date" />
			<h:panelGroup>
				<h:inputText id="tdate" value="#{employeeLeaveBean.toDate}"
					required="true" requiredMessage="From Date is mandatory"
					converterMessage="Invalid Date Format">
					<f:convertDateTime pattern="dd-MMM-yyyy" />
				</h:inputText>
				<a onclick="javascript:new NewCal('form:tdate','ddmmmyyyy')">
				<img src="cal.gif" width="16"
					height="16" border="0" alt="Pick a Date"></a>
			</h:panelGroup>
			<h:message for="tdate"></h:message>


		</h:panelGrid> <br>
		<h:commandButton value="Apply Leave"
			action="#{employeeLeaveBean.leaveApplication}"></h:commandButton></center>
	</h:form>

	<br>
	<center>
	<h:outputText value="#{employeeLeaveBean.message}"></h:outputText>
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