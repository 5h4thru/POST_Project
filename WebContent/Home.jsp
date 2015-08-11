<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function dis(){
 history.forward();
}
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Home Page</title>

</head>
<body onload="dis();">

<f:view>
<center>
<img src="hospitalheader.jpg" width="700"/>
<h2>Home Page</h2><br>
</center>
Logged in as <c:out value="${uname}"></c:out>
<center>
<c:choose>
<c:when test="${srole=='PA'}">
<table cellpadding="20" id="nav-menu" style="width:100%;">
<tr>
<td><h:outputLink value="ViewProfile.jsp">View Profile</h:outputLink></td>
<td><h:outputLink value="MakeAppointment.jsp">Make Appointment</h:outputLink></td>
<td><h:outputLink value="CancelAppointment.jsp">Cancel Appointment</h:outputLink></td>
<td><h:outputLink value="SearchDoctor.jsp">Search Doctor</h:outputLink></td>
<td><h:form><h:commandLink value="Logout" action="#{loginBean.logOut}"></h:commandLink></h:form></td>
</tr>
</table>
</c:when>
<c:when test="${srole=='DC'}">
<table cellpadding="20" id=nav-menu>
<tr>
<td><h:outputLink value="UpdateDoctorProfile.jsp">Update Profile</h:outputLink></td>
<td><h:outputLink value="ViewAppointment.jsp">My Appointments</h:outputLink></td>
<td><h:outputLink value="ScheduleSurgery.jsp">Schedule Surgery</h:outputLink></td>
<td><h:outputLink value="DrugRequestGenerate.jsp">Medicine Request</h:outputLink></td>
<td><h:outputLink value="ApplyForLeave.jsp">Apply Leave</h:outputLink></td>
<td><h:form><h:commandLink value="Logout" action="#{loginBean.logOut}"></h:commandLink></h:form></td>
</tr>
</table>
</c:when>
<c:when test="${srole=='RE'}">
<table cellpadding="20" id=nav-menu>
<tr>
<td><h:outputLink value="TariffEnquiry.jsp">Tariff Enquiry</h:outputLink></td>
<td><h:outputLink value="InpatientEnquiry.jsp">InPatientEnquiry</h:outputLink></td>
<td><h:outputLink value="BillingAndPayment.jsp">Billing</h:outputLink></td>
<td><h:outputLink value="ViewSchedule.jsp">View Doctors Schedule</h:outputLink></td>
<td><h:outputLink value="IPDRegistration.jsp">In-Patient Registration</h:outputLink></td>
<td><h:outputLink value="OPDRegistration.jsp">Out-Patient Registration</h:outputLink></td>
<td><h:form><h:commandLink value="Logout" action="#{loginBean.logOut}"></h:commandLink></h:form></td>
</tr>
</table>
</c:when>
<c:when test="${srole=='AD'}">
<table cellpadding="20" id=nav-menu>
<tr>
<td><h:outputLink value="EmployeeManagement.jsp">Employee Management</h:outputLink></td>
<td><h:outputLink value="DrugManagement.jsp">Drug Management</h:outputLink></td>
<td><h:outputLink value="WardManagement.jsp">Ward Management</h:outputLink></td>
<td><h:outputLink value="EquipmentManagement.jsp">Equipment Management</h:outputLink></td>
<td><h:outputLink value="PurchaseMedicine.jsp">Purchase Medicine</h:outputLink></td>
<td><h:outputLink value="ApproveRequest.jsp">Approve Request</h:outputLink></td>
<td><h:form><h:commandLink value="Logout" action="#{loginBean.logOut}"></h:commandLink></h:form></td>
</tr>
</table>
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