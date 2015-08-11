<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Billing And Payment</title>
</head>
<body>
<f:view>
<center>
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
	<h3>Billing</h3><br>
	<h:panelGrid border="1" columns="2">
		<h:outputText value="Enter Registration No"></h:outputText>
			<h:inputText id="regNo" value="#{billingBean.registrationNo}" required="true" requiredMessage="Registration NO required!!">
				<f:validator validatorId="rnv"/>
			</h:inputText>
			<h:outputText value="Enter Patient Id"></h:outputText>
			<h:inputText id="pID" value="#{billingBean.patientId}" required="true" requiredMessage="Patient ID required!!">
				<f:validator validatorId="piv"/>
			</h:inputText>
			
			<h:outputText value="Amount(only in case of pay)"></h:outputText>
			<h:inputText id="amt" value="#{billingBean.amount}"  validatorMessage="Amount has to be greater than zero!!" converterMessage="Invalid amount entered!!">
			<f:validateDoubleRange minimum="1.00" maximum="999999999.99">
			</f:validateDoubleRange>
			</h:inputText>
			<h:commandButton value="Pay" action="#{billingBean.payIPD}"></h:commandButton>
			<h:commandButton value="Generate Bill" action="#{billingBean.generateBill}"></h:commandButton>
			<h:commandButton value="Discharge"  action="#{billingBean.discharge}"></h:commandButton>
	</h:panelGrid>
	</h:form>
	<br>
	<br>
	<h:message for="regNo"></h:message><br>
	<h:message for="pID"></h:message><br>
	<h:message for="amt"></h:message><br>

	
	<h:outputText value="#{billingBean.message}"> </h:outputText>
	
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