<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Receipt</title>
</head>
<body>
<center>
<f:view>
	<img src="hospitalheader.jpg">
	<c:choose>
<c:when test="${srole=='RE'}">
	<h:form>
<table cellpadding="20" id=nav-menu>
<tr>
<td><h:outputLink value="TariffEnquiry.jsp">Tariff Enquiry</h:outputLink></td>
<td><h:outputLink value="InpatientEnquiry.jsp">InPatient Enquiry</h:outputLink></td>
<td><h:outputLink value="BillingAndPayment.jsp">Billing</h:outputLink></td>
<td><h:outputLink value="IPDRegistration.jsp">InPatient Registration</h:outputLink></td>
<td><h:outputLink value="OPDRegistration.jsp">OutPatient Registration</h:outputLink></td>
<td><h:outputLink value="Home.jsp">Home</h:outputLink></td>
</tr>
</table>
Logged in as <c:out value="${uname}"></c:out>
	<h2><h:outputText value="Receipt"></h:outputText></h2>
		<br>
		<br>
	<h:panelGrid border="1" columns="2">
			<h:outputText value="Bill NO"></h:outputText>
			<h:outputText value="#{billingBean.billNo}"></h:outputText>
			<h:outputText value="patient Id"></h:outputText>
			<h:outputText value="#{billingBean.patientId}"></h:outputText>
			<h:outputText value="Registration No"></h:outputText>
			<h:outputText value="#{billingBean.registrationNo}"></h:outputText>
			<h:outputText value="Description"></h:outputText>
			<h:outputText value="#{billingBean.description}"></h:outputText>
			<h:outputText value="Amount"></h:outputText>
			<h:outputText value="#{billingBean.amount}"></h:outputText>
	</h:panelGrid>
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