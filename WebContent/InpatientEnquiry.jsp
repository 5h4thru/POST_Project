<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inpatient Enquiry</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<f:view>
<div id="header"></div><br>
<c:choose>
<c:when test="${srole=='RE'}">
Logged in as <c:out value="${uname}"></c:out>
<center>
<h:panelGrid id="nav-menu" columns="6" cellpadding="3" cellspacing="10">
<h:outputLink value="TariffEnquiry.jsp" ><u>Tariff Enquiry</u></h:outputLink>
<h:outputLink value="InpatientEnquiry.jsp" ><u>InPatient Enquiry</u></h:outputLink>
<h:outputLink value="BillingAndPayment.jsp" ><u>Billing</u></h:outputLink>
<h:outputLink value="IPDRegistration.jsp" ><u>In-Patient Registration</u></h:outputLink>
<h:outputLink value="OPDRegistration.jsp" ><u>Out-Patient Registration</u></h:outputLink>
<h:outputLink value="Home.jsp" ><u>Home</u></h:outputLink>
</h:panelGrid>
<br>
<h2>InPatient Enquiry...</h2>
<br>
<h:form>
<h:outputLabel value="Patient's Username"></h:outputLabel>
<h:inputText value="#{inPatientEnquiryMB.patientUsername }" valueChangeListener="#{inPatientEnquiryMB.inpatientEnquiry}" onchange="submit()"  required="true" requiredMessage="Patient's Username is mandatory" validator="#{inPatientEnquiryMB.validate}"  id="id1">
</h:inputText><br><br>
<h:message for="id1"></h:message>
<h:panelGrid border="2" columns="2">
		<c:if test="${inPatientEnquiryMB.appointmentTO != null}">	
			<h:outputText value="Registration No."></h:outputText>
			<h:outputText value="#{inPatientEnquiryMB.appointmentTO.registrationNo }"> </h:outputText>
			<h:outputText value="Doctor."></h:outputText>
			<h:outputText value="#{inPatientEnquiryMB.appointmentTO.doctorName }"> </h:outputText>
			<h:outputText value="Department."></h:outputText>
			<h:outputText value="#{inPatientEnquiryMB.appointmentTO.department }"> </h:outputText>
			<h:outputText value="Ward No."></h:outputText>
			<h:outputText value="#{inPatientEnquiryMB.appointmentTO.wardNo}"> </h:outputText>
			<h:outputText value="Bed No."></h:outputText>
			<h:outputText value="#{inPatientEnquiryMB.appointmentTO.bedNo}"> </h:outputText>
			<h:outputText value="Date Of Admission."></h:outputText>
			<h:outputText value="#{inPatientEnquiryMB.appointmentTO.dateOfAdmission}"> 
			<f:convertDateTime pattern="dd-MMM-yyyy"/>
			</h:outputText>
			<h:outputText value="Reason For Admission."></h:outputText>
			<h:outputText value="#{inPatientEnquiryMB.appointmentTO.reasonForAdmission}"> </h:outputText>
		</c:if>	
		<c:if test="${inPatientEnquiryMB.appointmentTO == null}">
		<h:outputLabel value="#{inPatientEnquiryMB.message}"> </h:outputLabel>
		</c:if>
			</h:panelGrid>
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