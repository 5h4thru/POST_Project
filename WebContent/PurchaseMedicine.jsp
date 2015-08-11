<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" href="style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Purchase Medicine</title>
</head>
<body>
<f:view>
<center>
<img src="hospitalheader.jpg"><br><br><br><br>
</center>
<c:choose>
<c:when test="${srole=='AD'}">
<center>
<table cellpadding="20" id=nav-menu>
<tr>
<td><h:outputLink value="EmployeeManagement.jsp">Employee Management</h:outputLink></td>
<td><h:outputLink value="DrugManagement.jsp">Drug Management</h:outputLink></td>
<td><h:outputLink value="WardManagement.jsp">Ward Management</h:outputLink></td>
<td><h:outputLink value="EquipmentManagement.jsp">Equipment Management</h:outputLink></td>
<td><h:outputLink value="PurchaseMedicine.jsp">Purchase Medicine</h:outputLink></td>
<td><h:outputLink value="ApproveRequest.jsp">Approve Request</h:outputLink></td>
<td><h:outputLink value="Home.jsp">Home</h:outputLink></td>
</tr>
</table>
<h2>Purchase Medicine</h2></center>
Logged in as <c:out value="${uname}">

		
	</c:out>
	<center>
	<h:form>
	<h:panelGrid border="1" columns="2">
			<h:outputText value="Registration No"/>
			
			<h:inputText value="#{medicinePurchaseBean.registrationNo}"  required="true" requiredMessage="Registration No. is Mandatory!!!"></h:inputText>
		<h:outputText value="Patient Type"/>
			
			<h:selectOneRadio value="#{medicinePurchaseBean.patientType}" required="true" requiredMessage="Patient Type is Mandatory!!!">
				<f:selectItem itemLabel="IPD" itemValue="IPD"></f:selectItem>
				<f:selectItem itemLabel="OPD" itemValue="OPD"></f:selectItem>
			</h:selectOneRadio>
			
		<h:outputText value="Select Drug"/>
			<h:selectOneMenu value="#{medicinePurchaseBean.medicineName}" valueChangeListener="#{medicinePurchaseBean.medicinePrice}" onchange="submit()" required="true" requiredMessage="Select Drug is Mandatory!!!">
				<f:selectItem itemLabel="Select"/>
				<f:selectItems value="#{medicinePurchaseBean.drugList}"  ></f:selectItems>
			</h:selectOneMenu>
		
		<c:if test="${medicinePurchaseBean.price ne 0}">
		<h:outputText value="Price"/>
		<h:outputText value="#{medicinePurchaseBean.price}"></h:outputText>
		</c:if>
		
			<h:outputText value="Enter Quantity"/>
			<h:inputText value="#{medicinePurchaseBean.quantity}" required="true" requiredMessage="Quantity is Mandatory!!!" converterMessage="Quantity should be an Integer" validatorMessage="Quantity should be greater than 0">
				<f:validateLongRange minimum="1"></f:validateLongRange>
			</h:inputText>
			
		</h:panelGrid><br><br>
		<h:outputText value="#{medicinePurchaseBean.message}"></h:outputText><br><br>
		<h:messages></h:messages><br>
		<h:commandButton value="Add" action="#{medicinePurchaseBean.addMedicine}"></h:commandButton>
		<h:commandButton value="Make Payment" action="#{medicinePurchaseBean.makePayment}"></h:commandButton>
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