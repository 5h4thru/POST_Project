<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="datetimepicker.js"></script>
<script type="text/javascript" src="datetimepicker.js"></script>
<link rel="stylesheet" href="style.css" style="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modify Employee</title>
</head>
<body>
<center><img src="hospitalheader.jpg">
</center> <f:view>
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
	<h:panelGrid columns="3" cellpadding="10">
		<h:outputLink value="AddNewEmployee.jsp">Add New Employees</h:outputLink>
		<h:outputLink value="DeleteEmployee.jsp">Delete Existing Employees</h:outputLink>
		<h:outputLink value="ModifyEmployee.jsp">Modify Employees</h:outputLink>
	</h:panelGrid>
	<h:form id="form1">
		<h:outputText value="Modify Form" />
		<h:panelGrid border="1" columns="2">
			<h:outputText value="User Name"></h:outputText>
			<h:selectOneMenu id="sel" value="#{bean.employeeTO.userName}"
				valueChangeListener="#{bean.storeUserName}" onchange="submit();"
				required="true" requiredMessage="Please select an employee">
				<f:selectItem itemLabel="--select--" />
				<f:selectItems value="#{bean.docList}" />
			</h:selectOneMenu>

		</h:panelGrid>

		<br>
		<c:if test="${bean.employeeTO.userName!=null}">

			<h:dataTable border="1" value="#{bean.toList}" var="row">
				<h:column id="name">
					<f:facet name="header">
						<h:outputText value="Employee Name"></h:outputText>
					</f:facet>
					<h:inputText id="einame" value="#{row.employeeName}"
						required="true" requiredMessage="Employee Name is mandatory">
						<f:validator validatorId="nVal" />
					</h:inputText>
					<h:message for="einame"></h:message>
				</h:column>

				<h:column id="uName">
					<f:facet name="header">
						<h:outputText value="User Name"></h:outputText>
					</f:facet>
					<h:outputText value="#{row.userName}" />
				</h:column>

				<h:column id="cdate">
					<f:facet name="header">
						<h:outputText value="Date Of Joining"></h:outputText>
					</f:facet>
					<h:inputText id="date" value="#{row.dateOfJoining}" required="true"
						requiredMessage="Date Of Joining is mandatory"
						converterMessage="Invalid Date Format">
						<f:convertDateTime pattern="dd-MMM-yyyy" />
					</h:inputText>
					<h:message for="date"></h:message>
				</h:column>

				<h:column id="qualification">
					<f:facet name="header">
						<h:outputText value="Qualification"></h:outputText>
					</f:facet>
					<h:inputText id="iqual" value="#{row.qualification}"
						required="true" requiredMessage="Qualification is mandatory">
						<f:validator validatorId="qVal" />
					</h:inputText>
					<h:message for="iqual"></h:message>
				</h:column>

				<h:column id="address">
					<f:facet name="header">
						<h:outputText value="Address"></h:outputText>
					</f:facet>
					<h:inputTextarea rows="3" id="iadd" value="#{row.address}"
						required="true" requiredMessage="Address is mandatory"
						validatorMessage="Cannot have more than 80 characters">
						<f:validateLength maximum="80" />
					</h:inputTextarea>
					<h:message for="iadd"></h:message>
				</h:column>

				<h:column id="phone">
					<f:facet name="header">
						<h:outputText value="Phone No."></h:outputText>
					</f:facet>
					<h:inputText id="iphone" value="#{row.phoneNo}" required="true"
						requiredMessage="Phone Number is mandatory"
						converterMessage="Only digits are allowed in Phone Number"
						validatorMessage="Should contain maximum of 12 digits (including country code and local code)">
						<f:validateLength maximum="12" />
					</h:inputText>
					<h:message for="iphone"></h:message>
				</h:column>

				<h:column id="des">
					<f:facet name="header">
						<h:outputText value="Designation"></h:outputText>
					</f:facet>
					<h:inputText id="ides" value="#{row.designation}" required="true"
						requiredMessage="Designation is mandatory">
						<f:validator validatorId="desDocVal" />
					</h:inputText>
					<h:message for="ides"></h:message>
				</h:column>

				<h:column id="cons">
					<f:facet name="header">
						<h:outputText value="Consultation"></h:outputText>
					</f:facet>
					<h:inputText id="icon" value="#{row.consultationFees}"
						required="true" requiredMessage="Consultation Fees is mandatory"
						converterMessage="Enter only numbers"
						validatorMessage="Cannot be more than 4 digits">
						<f:validateDoubleRange minimum="1" maximum="9999"></f:validateDoubleRange>
						<f:validateLength minimum="1" maximum="4" />
					</h:inputText>
					<h:message for="icon"></h:message>
				</h:column>

				<h:column id="dept">
					<f:facet name="header">
						<h:outputText value="Department"></h:outputText>
					</f:facet>
					<h:inputText id="idept" value="#{row.department}" required="true"
						requiredMessage="Department is mandatory">
					</h:inputText>
					<h:message for="idept"></h:message>
				</h:column>

			</h:dataTable>

		</c:if>

		<br>
		<h:commandButton value="Update" action="#{bean.updateEmployee}"></h:commandButton>
		<br>
		<h:message for="sel"></h:message>
	</h:form>
	<br>
	<h:outputText value="#{bean.message}" />
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