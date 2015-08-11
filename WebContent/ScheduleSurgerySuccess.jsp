<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" href="style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule Surgery Success</title>
</head>
<body>
<f:view>
<center>
<img src="hospitalheader.jpg"><br><br><br></center>
<c:choose>
<c:when test="${srole=='DC'}">
<center>
<table cellpadding="20" id=nav-menu>
<tr>
<td><h:outputLink value="UpdateDoctorProfile.jsp">Update Profile</h:outputLink></td>
<td><h:outputLink value="ViewAppointment.jsp">My Appointments</h:outputLink></td>
<td><h:outputLink value="ScheduleSurgery.jsp">Schedule Surgery</h:outputLink></td>
<td><h:outputLink value="DrugRequestGenerate.jsp">Medicine Request</h:outputLink></td>
<td><h:outputLink value="Home.jsp">Home</h:outputLink></td>
</tr>
</table><br><br><br>
<h2>OT Registration Successful</h2>
<h:outputText value="#{doctorBean.succSurMsg}"></h:outputText>
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