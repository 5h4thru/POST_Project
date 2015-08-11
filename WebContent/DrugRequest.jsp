<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Drug Request</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<CENTER>
<div id="header"></div></center>
<center>
<f:view>
<c:choose>
<c:when test="${srole=='AD'}">
Logged in as <c:out value="${uname}"></c:out>
<h:panelGrid id="nav-menu" columns="7" cellpadding="3" cellspacing="8">
<h:outputLink value="EmployeeManagement.jsp" ><u>Employee Management</u></h:outputLink>
<h:outputLink value="DrugManagement.jsp" ><u>Drug Management</u></h:outputLink>
<h:outputLink value="WardManagement.jsp" ><u>Ward Management</u></h:outputLink>
<h:outputLink value="EquipmentManagement.jsp" ><u>Equipment Management</u></h:outputLink>
<h:outputLink value="PurchaseMedicine.jsp" ><u>Purchase Medicine</u></h:outputLink>
<h:outputLink value="ApproveRequest.jsp" ><u>ApproveRequest</u></h:outputLink>
<h:outputLink value="Home.jsp" ><u>Home</u></h:outputLink>
</h:panelGrid>
<h:form>
<h:outputLabel>Request Approval</h:outputLabel><br>
<c:if test="${approvalBean.mstatus==1}">
<h:dataTable  value="#{approvalBean.drugRequests}" border="2"  var="row" >
<h:column>
<f:facet name="header">
<h:outputText value="Doctor Id."></h:outputText>
</f:facet>
<h:outputText value="#{row.doctorId}"></h:outputText>
</h:column>

<h:column>
<f:facet name="header">
<h:outputText value="Drug Name."></h:outputText>
</f:facet>
<h:outputText value="#{row.drugName}"></h:outputText>
</h:column>

<h:column>
<f:facet name="header">
<h:outputText value="Quantity."></h:outputText>
</f:facet>
<h:outputText value="#{row.quantity}"></h:outputText>
</h:column>

<h:column>
<f:facet name="header">
<h:outputText value="Company."></h:outputText>
</f:facet>
<h:outputText value="#{row.manufacturer}"></h:outputText>
</h:column>

<h:column>
<f:facet name="header">
<h:outputText value="CostPerUnit."></h:outputText>
</f:facet>
<h:inputText value="#{row.costPerUnit}" validator="#{approvalBean.costperunitvalidate}" converterMessage="Enter only numbers" id="id1">
</h:inputText>
</h:column>
<h:column>
<f:facet name="header">
<h:outputText value="Expiry Date."></h:outputText>
</f:facet>
<h:inputText value="#{row.dateOfExpiry}" converterMessage="Enter a valid Date" id="id2">
<f:convertDateTime pattern="dd-MMM-yyyy"/>
</h:inputText>
</h:column>

<h:column>
<f:facet name="header">
<h:outputText value="Requests."></h:outputText>
</f:facet>
<h:selectBooleanCheckbox  value="#{row.selected}"></h:selectBooleanCheckbox>
</h:column>

</h:dataTable>
<h:commandButton value="Accept" action="#{approvalBean.updateAcceptDrug}"></h:commandButton>
<h:commandButton value="Reject" action="#{approvalBean.updateRejectDrug}"></h:commandButton><br>
<h:message for="id1"></h:message><br>
<h:message for="id2"></h:message><br>
<h:messages></h:messages>
</c:if>
<h:outputLabel value="#{approvalBean.message1}"></h:outputLabel>
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
</CENTER>
</body>
</html>


