<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ipd Registration</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<center>
<img alt="" src="hospitalheader.jpg">
<br>
<br>
<br>
<f:view>
<c:choose>
<c:when test="${srole=='RE'}">
<table cellpadding="20" id="nav-menu">
<tr>
<td><h:outputLink value="TariffEnquiry.jsp">Tarif Enquiry</h:outputLink></td>
<td><h:outputLink value="InpatientEnquiry.jsp">Inpatient Enquiry</h:outputLink></td>
<td><h:outputLink value="BillingAndPayment.jsp">Billing</h:outputLink></td>
<td><h:outputLink value="IPDRegistration.jsp">In-Patient Registration</h:outputLink></td>
<td><h:outputLink value="OPDRegistration.jsp">Out-Patient Registration</h:outputLink></td>
<td><h:outputLink value="HomePage.jsp">Home</h:outputLink></td>
</tr>
</table>
<br>
<br>
<h:form>
<h3>IPD Registration</h3>
<br>
<br>
<h:panelGrid columns="3" border="1">
<h:outputText value="Patient Id."></h:outputText>
<h:inputText id="patientId" required="true" requiredMessage="patientId required" value="#{iPDRegistrationBean.appointmentTO.username}">
<f:validator validatorId="piv"/>
</h:inputText>
<h:message for="patientId"></h:message>
<h:outputText value="Doctor."></h:outputText>
<h:selectOneMenu id="doctor" required="true" requiredMessage="please select the doctor" value="#{iPDRegistrationBean.appointmentTO.doctorId}" valueChangeListener="#{iPDRegistrationBean.getDepartmentName}" onchange="submit()">
<f:selectItem itemValue="" itemLabel="--SELECT--"/>
<f:selectItems value="#{iPDRegistrationBean.doctors}"/>
</h:selectOneMenu>
<h:message for="doctor"></h:message>
<h:outputText value="Department."></h:outputText>
<h:outputText id="dep" value="#{iPDRegistrationBean.department}"></h:outputText>
<h:message for="dep"></h:message>
<h:outputText value="Ward Type."></h:outputText>
<h:selectOneMenu value="#{iPDRegistrationBean.wardType}" valueChangeListener="#{iPDRegistrationBean.getWardsList}" id="wType" required="true" requiredMessage="please select ward type" onchange="submit()">
<f:selectItem itemLabel="select" itemValue=""/>
<f:selectItem itemLabel="GENERAL" itemValue="GEN"/>
<f:selectItem itemLabel="PRIVATE" itemValue="PRI"/>
</h:selectOneMenu>
<h:message for="wType"></h:message>
<h:outputText value="Ward no."></h:outputText>
<h:selectOneMenu value="#{iPDRegistrationBean.appointmentTO.wardNo}" valueChangeListener="#{iPDRegistrationBean.getBedsList}" id="wNo" required="true" requiredMessage="please select the ward No" onchange="submit()">
<f:selectItem itemLabel="--SELECT--" itemValue=""/>
<f:selectItems value="#{iPDRegistrationBean.wards}"/>
</h:selectOneMenu>
<h:message for="wNo"></h:message>
<h:outputText value="Bed No."></h:outputText>
<h:selectOneMenu value="#{iPDRegistrationBean.appointmentTO.bedNo}"  id="bed" required="true" requiredMessage="please select the bed No" >
<f:selectItem itemLabel="--SELECT--" itemValue=""/>
<f:selectItems value="#{iPDRegistrationBean.beds}"/>
</h:selectOneMenu>
<h:message for="bed"></h:message>
<h:outputText value="Reason for Admission."></h:outputText>
<h:inputTextarea rows="4" value="#{iPDRegistrationBean.appointmentTO.reasonForAdmission}" id="reasonAd" required="true" requiredMessage="please enter the reason"></h:inputTextarea>
<h:message for="reasonAd"></h:message>
</h:panelGrid>
<br>
<br>
<h:commandButton action="#{iPDRegistrationBean.addPatient}" value="Submit"></h:commandButton><br><br><br>
<h:outputText value="#{iPDRegistrationBean.message}"></h:outputText>
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