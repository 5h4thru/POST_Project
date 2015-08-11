<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Generate Bill</title>
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
	<h2>Bill Details</h2><br>
	<h:dataTable border="1" value="#{billingBean.list}" var="row">
			<h:column id="column1">
				<f:facet name="header">
					<h:outputText value="Bill No"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.billNo}"></h:outputText>
			</h:column>
			<h:column id="column2">
				<f:facet name="header">
					<h:outputText value="patient Id"></h:outputText>
				</f:facet>
				<h:outputText value="#{billingBean.patientId}"></h:outputText>
			</h:column>
			<h:column id="column3">
				<f:facet name="header">
					<h:outputText value="Date"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.billingDate}">
							<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/>
				</h:outputText>
				
			</h:column>
			<h:column id="column4">
				<f:facet name="header">
					<h:outputText value="Description"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.description}"></h:outputText>
			</h:column>
			<h:column id="column5">
				<f:facet name="header">
					<h:outputText value="Amount"></h:outputText>
				</f:facet>
				<h:outputText value="#{row.amount}"></h:outputText>
			</h:column>
		</h:dataTable>
		<h:panelGrid border="1" columns="2">
			<h:outputText value="Date"></h:outputText>
			
			<h:outputText value="#{billingBean.billingDate}">
				<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/>
			</h:outputText>
			<h:outputText value="Total"></h:outputText>
			<h:outputText value="#{billingBean.total}"></h:outputText>
			<h:outputText value="Advance"></h:outputText>
			<h:outputText value="#{billingBean.advance}"></h:outputText>
			<h:outputText value="Balance Amount"></h:outputText>
			<h:outputText value="#{billingBean.balance}"></h:outputText>
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
</center>
</f:view>
</body>
</html>