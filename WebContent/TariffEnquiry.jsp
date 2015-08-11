<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tariff Enquiry</title>
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
		<h3><h:outputText value="Tariff Enquiry"></h:outputText></h3>
		<br>
		<h:panelGrid border="1" columns="3">
			<h:outputText value="Doctor"></h:outputText>
			<h:selectOneMenu id="doctor" value="#{tariffBean.doctorId}" valueChangeListener="#{tariffBean.docCost}"  onchange="submit()" >
				<f:selectItem itemLabel="---Select---" itemValue=""/>
				<f:selectItems value="#{tariffBean.doctors}"/>
			</h:selectOneMenu>
			<h:outputText value="#{tariffBean.docMsg}"></h:outputText>
			<h:outputText value="Ward"></h:outputText>
			<h:selectOneMenu id="ward" value="#{tariffBean.wardType}" valueChangeListener="#{tariffBean.wardCost}" onchange="submit()">
				<f:selectItem itemLabel="---Select---" itemValue=""/>
				<f:selectItems value="#{tariffBean.wards}"/>
			</h:selectOneMenu>
			<h:outputText value="#{tariffBean.wardMsg}"></h:outputText>
			<h:outputText value="Operation Theatre"></h:outputText>
			<h:selectOneMenu id="ot" valueChangeListener="#{tariffBean.otCost}" onchange="submit()">
				<f:selectItem itemLabel="---Select---" itemValue=""/>
				<f:selectItems value="#{tariffBean.ots}"/>
			</h:selectOneMenu>
			<h:outputText value="#{tariffBean.otMsg}"></h:outputText>
	</h:panelGrid>
	<br><br><h:outputText value="#{tariffBean.msg}"></h:outputText>
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