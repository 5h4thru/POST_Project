<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>OPD Registration</title>
<link rel="stylesheet" type="text/css" href="style.css">
<script type="text/javascript" src="datetimepicker.js"></script>
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
<td><h:outputLink value="TariffEnquiry.jsp">Tariff Enquiry</h:outputLink></td>
<td><h:outputLink value="InpatientEnquiry.jsp">Inpatient Enquiry</h:outputLink></td>
<td><h:outputLink value="BillingAndPayment.jsp">Billing</h:outputLink></td>
<td><h:outputLink value="IPDRegistration.jsp">In-Patient Registration</h:outputLink></td>
<td><h:outputLink value="OPDRegistration.jsp">Out-Patient Registration</h:outputLink></td>
<td><h:outputLink value="Home.jsp">Home</h:outputLink></td>
</tr>
</table>
<br><br><br>
<h:form>
<h3>OPD Registration</h3>
<br>
<h:panelGrid columns="3">
<h:commandLink value="New patient" action="#{oPDRegistrationBean.kindOfPatient}">
<f:param name="patient" value="1"></f:param>
</h:commandLink>
&nbsp;&nbsp;&nbsp;&nbsp;
<h:commandLink value="Existing patient" action="#{oPDRegistrationBean.kindOfPatient}">
<f:param name="patient" value="2"></f:param>
</h:commandLink>
</h:panelGrid>
<br>
</h:form>


<c:if test="${oPDRegistrationBean.patientType==1}">
<h:form>
<h5>New Patient Registration.</h5> 
<br>
<h:panelGrid columns="3" border="1">
<h:outputText value="First Name."></h:outputText>
<h:inputText value="#{oPDRegistrationBean.patientTO.firstName}" id="fName" required="true" requiredMessage="First Name required">
<f:validator validatorId="nv" />
</h:inputText>
<h:message for="fName"></h:message>
<h:outputText value="Last Name."></h:outputText>
<h:inputText value="#{oPDRegistrationBean.patientTO.lastName}" id="lName" required="true" requiredMessage="Last Name required">
<f:validator validatorId="nv" />
</h:inputText>
<h:message for="lName"></h:message>
<h:outputText value="Address."></h:outputText>
<h:inputTextarea value="#{oPDRegistrationBean.patientTO.address}" id="address" required="true" requiredMessage="Address required"></h:inputTextarea>
<h:message for="address"></h:message>
<h:outputText value="PhoneNo."></h:outputText>
<h:inputText value="#{oPDRegistrationBean.patientTO.phoneNo}" id="phone" required="true" requiredMessage="PhoneNo required" converterMessage="only numbers are allowed">
<f:validator validatorId="phnv" />
</h:inputText>
<h:message for="phone"></h:message>
<h:outputText value="Age."></h:outputText>
<h:inputText value="#{oPDRegistrationBean.patientTO.age}" required="true" requiredMessage="Age required" id="age" converterMessage="only numbers are allowed" validatorMessage="Enter age between 1 and 100">
<f:validateLongRange minimum="1" maximum="100"></f:validateLongRange>
</h:inputText>
<h:message for="age"></h:message>
<h:outputText value="Nationality."></h:outputText>
<h:inputText value="#{oPDRegistrationBean.patientTO.nationality}" id="nationality" required="true" requiredMessage="Nationality required">
<f:validator validatorId="nv" /></h:inputText>
<h:message for="nationality"></h:message>
<h:outputText value="Contact Person."></h:outputText>
<h:inputText value="#{oPDRegistrationBean.patientTO.contactPerson}" id="cPerson" required="true" requiredMessage="Contact Person required">
<f:validator validatorId="nv" /></h:inputText>
<h:message for="cPerson"></h:message>
<h:outputText value="Reason For Admission."></h:outputText>
<h:inputTextarea value="#{oPDRegistrationBean.opdAppointmentTO.reasonForAppointment}" id="reason" required="true" requiredMessage="Reason required">
<f:validator validatorId="nv" />
</h:inputTextarea>
<h:message for="reason"></h:message>
</h:panelGrid>
<br>
<br>
<h:commandButton value="Submit" action="#{oPDRegistrationBean.addPatient}"></h:commandButton><br>

<br>
<h:outputText value="#{oPDRegistrationBean.message}"></h:outputText>
</h:form>

<h:form id="form1">
<h:panelGrid columns="5">
<h:outputLabel value="Schedule Appointments"></h:outputLabel>
<h:selectOneMenu value="#{oPDRegistrationBean.opdAppointmentTO.doctorId}" valueChangeListener="#{oPDRegistrationBean.getAvailableSlots}" onchange="submit();" required="true" requiredMessage="Doctor name Mandatory">
<f:selectItem itemLabel="-DOCTOR-" />
<f:selectItems value="#{oPDRegistrationBean.doctors}" />
</h:selectOneMenu>
<h:inputText value="#{oPDRegistrationBean.opdAppointmentTO.dateOfAdmission}" id="date" required="true" requiredMessage="Date is Mandatory" converterMessage="Date Format should be dd-MMM-yyyy">
<f:convertDateTime pattern="dd-MMM-yyyy" />
<f:validator validatorId="adv" />
</h:inputText>
<a href="javascript:NewCal('form1:date','ddmmmyyyy')">
<img src="cal.gif" width="16" height="16" border="0" alt="Pick a Date">
</a>
<h:selectOneMenu value="#{oPDRegistrationBean.opdAppointmentTO.slot}" required="true" requiredMessage="Slot is Mandatory">
<f:selectItem itemLabel="-SLOT-" />
<f:selectItems value="#{oPDRegistrationBean.slots}" />
</h:selectOneMenu>
</h:panelGrid><br><br><br>
<h:panelGrid columns="2">
<h:outputLabel value="Accept Cash"></h:outputLabel>
<h:inputText value="#{oPDRegistrationBean.billingTO.amount}" required="true" requiredMessage="Cash is mandatory" validatorMessage="Consultation fees should be less than 10000" converterMessage="Enter only digits without any spaces in Accept Cash field">
<f:validateDoubleRange minimum="1" maximum="9999"></f:validateDoubleRange>
</h:inputText>
</h:panelGrid><br><br>
<h:commandButton value="Schedule and Generate Bill" action="#{oPDRegistrationBean.scheduleAndGenerateBill}"></h:commandButton><br><br>
<h:outputText value="#{oPDRegistrationBean.message}"></h:outputText><br>
<h:messages></h:messages>
</h:form>
</c:if>

<c:if test="${oPDRegistrationBean.patientType==2}">
<h:form id="form1">
<h5>Existing patient registration.</h5>
<br>
<h:panelGrid columns="3" border="1">  
<h:outputText value="Patient Id."></h:outputText>
<h:inputText value="#{oPDRegistrationBean.opdAppointmentTO.patientId}" id="patId" required="true" requiredMessage="Patient Id required">
<f:validator validatorId="piv"/>
</h:inputText>
<h:message for="patId"></h:message>
<h:outputText value="Reason For Admission."></h:outputText>
<h:inputTextarea value="#{oPDRegistrationBean.opdAppointmentTO.reasonForAppointment}" id="reason" required="true" requiredMessage="Reason required">
</h:inputTextarea>
<h:message for="reason"></h:message>
</h:panelGrid><br>

<h:panelGrid columns="5">
<h:outputLabel value="Schedule Appointments"></h:outputLabel>
<h:selectOneMenu value="#{oPDRegistrationBean.opdAppointmentTO.doctorId}" valueChangeListener="#{oPDRegistrationBean.getAvailableSlots}" onchange="submit();" required="true" requiredMessage="Doctor name is Mandatory">
<f:selectItem itemLabel="-DOCTOR-" />
<f:selectItems value="#{oPDRegistrationBean.doctors}" />
</h:selectOneMenu>
<h:inputText value="#{oPDRegistrationBean.opdAppointmentTO.dateOfAdmission}" id="date" required="true" requiredMessage="Date is Mandatory" converterMessage="Date Format should be dd-MMM-yyyy">
<f:convertDateTime pattern="dd-MMM-yyyy" />
<f:validator validatorId="adv" />
</h:inputText>
<a href="javascript:NewCal('form1:date','ddmmmyyyy')">
<img src="cal.gif" width="16" height="16" border="0" alt="Pick a Date">
</a>
<h:selectOneMenu value="#{oPDRegistrationBean.opdAppointmentTO.slot}" required="true" requiredMessage="Slot is Mandatory">
<f:selectItem itemLabel="-SLOT-" />
<f:selectItems value="#{oPDRegistrationBean.slots}" />
</h:selectOneMenu>
</h:panelGrid><br><br><br>
<h:panelGrid columns="2">
<h:outputLabel value="Accept Cash"></h:outputLabel>
<h:inputText value="#{oPDRegistrationBean.billingTO.amount}" required="true" requiredMessage="Cash is mandatory" validatorMessage="Cash should be less than 10000" converterMessage="Enter only digits">
<f:validateDoubleRange minimum="1" maximum="9999"></f:validateDoubleRange>
</h:inputText>
</h:panelGrid><br><br>
<h:commandButton value="Schedule and Generate Bill" action="#{oPDRegistrationBean.scheduleAndGenerateBill}"></h:commandButton><br><br>
<h:outputText value="#{oPDRegistrationBean.message}"></h:outputText><br>
<h:messages></h:messages>
</h:form>
</c:if>
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