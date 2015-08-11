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
<title>Profile Change</title>
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
		<h:outputText value="Request Approvals"></h:outputText>
		<br>
		<h:panelGrid columns="2" cellpadding="10">
			<h:outputLink value="ProfileChange.jsp">Profile Change Requests</h:outputLink>
			<h:outputLink value="DrugRequest.jsp">Drug Requests</h:outputLink>
		</h:panelGrid>

		<c:if test="${not empty approvalBean.proRequests}">
			<h:dataTable border="1" value="#{approvalBean.proRequests}" var="row">
				<h:column>
					<f:facet name="header">
						<h:outputText value="DoctorId"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.doctorId}"></h:outputText>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Doctor Name"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.employeeName}"></h:outputText>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Department"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.department}"></h:outputText>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Qualification"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.qualification}"></h:outputText>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Consultation Fees"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.consultationFees}"></h:outputText>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Phone No."></h:outputText>
					</f:facet>
					<h:outputText value="#{row.phoneNo}"></h:outputText>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Address"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.address}"></h:outputText>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Requests"></h:outputText>
					</f:facet>
					<h:selectBooleanCheckbox value="#{row.selected}">
					</h:selectBooleanCheckbox>
				</h:column>
			</h:dataTable>
			<br>
			<h:panelGrid cellspacing="10" columns="2">
				<h:commandButton value="Approve"
					action="#{approvalBean.updateAcceptProfile}"></h:commandButton>
				<h:commandButton value="Reject"
					action="#{approvalBean.updateRejectProfile}"></h:commandButton>
			</h:panelGrid>
		</c:if>


	</h:form>
	<h:outputText value="#{approvalBean.message}"></h:outputText>
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