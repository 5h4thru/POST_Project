<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="style.css" style="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Management</title>
</head>
<body>
<center><img src="hospitalheader.jpg">
</center>
 <f:view>
 <c:choose>
<c:when test="${srole=='AD'}">
Logged in as <c:out value="${uname}"></c:out>
<center>
	<h:panelGrid id="nav-menu" columns="7" cellpadding="10"
		cellspacing="10">
		<h:outputLink value="EmployeeManagement.jsp">
			<u>Employee Management</u>
		</h:outputLink>
		<h:outputLink value="DrugManagement.jsp">
			<u>Drug Management</u>
		</h:outputLink>
		<h:outputLink value="WardManagement.jsp">
			<u>Ward Management</u>
		</h:outputLink>
		<h:outputLink value="EquipmentManagement.jsp">
			<u>Equipment Management</u>
		</h:outputLink>
		<h:outputLink value="PurchaseMedicine.jsp">
			<u>Purchase Medicine</u>
		</h:outputLink>
		<h:outputLink value="ApproveRequest.jsp">
			<u>Approve Request</u>
		</h:outputLink>
		<h:outputLink value="Home.jsp">
			<u>Home</u>
		</h:outputLink>
	</h:panelGrid>

	<h:form>
		<h:panelGrid columns="3" cellpadding="10">
			<h:outputLink value="AddNewEmployee.jsp">Add New Employees</h:outputLink>
			<h:outputLink value="DeleteEmployee.jsp">Delete Existing Employees</h:outputLink>
			<h:outputLink value="ModifyEmployee.jsp">Modify Employees</h:outputLink>
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