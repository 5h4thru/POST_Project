<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Payment</title>
<link href="style.css" rel="stylesheet" type="text/css">
</head>
<body>
<center id="header"></center> 
<center>
<f:view>
<c:choose>
<c:when test="${srole=='PA'}">
<h5 align="left"> Logged in as <h:outputText value="#{patientBean.patientId}"></h:outputText></h5><br>


<h3>Payment Details</h3><br>
	<h:dataTable border="1" value="#{patientBean.paymentList}" var="row">
		<h:column id="paymentNo">
			<f:facet name="header">
				<h:outputText value="Payment No"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.payNo}"></h:outputText>
		</h:column>
		<h:column id="doctorId">
			<f:facet name="header">
				<h:outputText value="Doctor Id"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.doctorId}"></h:outputText>
		</h:column>
		<h:column id="dateOfPayment">
			<f:facet name="header">
				<h:outputText value="Date Of Payment"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.dateOfPayment}">
			<f:convertDateTime pattern="dd-MMM-yyyy"/>
			</h:outputText>
		</h:column>
		<h:column id="amount">
			<f:facet name="header">
				<h:outputText value="Amount"></h:outputText>
			</f:facet>
			<h:outputText value="#{row.amount}"></h:outputText>
		</h:column>
	</h:dataTable>
<br><br>

<h:outputLink value="Home.jsp"><h5>Home</h5></h:outputLink>
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