<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="datetimepicker.js"></script>
<script type="text/javascript">
	function dateFun() {
		NewCal('form1:date', 'ddmmmyyyy');
	}
</script>
<link rel="stylesheet" href="style.css" style="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<body>

<center><img src="hospitalheader.jpg"> </center>

<f:view>
<c:choose>
<c:when test="${srole=='AD'}">
Logged in as <c:out value="${uname}"></c:out>
<center>

	<br>
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
	<h:form>
		<h:selectOneMenu value="#{bean.select}" onchange="submit();">
			<f:selectItem itemValue="Receptionist" itemLabel="Receptionist" />
			<f:selectItem itemValue="Doctor" itemLabel="Doctor" />
		</h:selectOneMenu>
	</h:form>
	<c:if test="${bean.select==\"Doctor\"}">
		<h:form id="form1">
			<h:panelGrid border="1" columns="3">
				<h:outputText value="Employee Name:" />
				<h:inputText id="name" value="#{bean.employeeTO.employeeName}"
					required="true" requiredMessage="Employee Name is mandatory">
					<f:validator validatorId="nVal" />
				</h:inputText>
				<h:message for="name"></h:message>


				<h:outputText value="Date Of Joining:" />
				<h:panelGroup>
					<h:inputText id="date" value="#{bean.employeeTO.dateOfJoining}"
						required="true" requiredMessage="Date Of Joining is mandatory"
						converterMessage="Invalid Date Format">
						<f:convertDateTime pattern="dd-MMM-yyyy" />
					</h:inputText>
					<a onclick="dateFun();"> <img src="cal.gif" width="16"
						height="16" border="0" alt="Pick a Date"></a>
				</h:panelGroup>
				<h:message for="date"></h:message>


				<h:outputText value="Qualification:" />
				<h:inputText id="qual" value="#{bean.employeeTO.qualification}"
					required="true" requiredMessage="Qualification is mandatory">
					<f:validator validatorId="qVal" />
				</h:inputText>
				<h:message for="qual"></h:message>


				<h:outputText value="Address:" />
				<h:inputTextarea rows="3" id="add"
					value="#{bean.employeeTO.address}" required="true"
					requiredMessage="Address is mandatory"
					validatorMessage="Cannot have more than 80 characters">
					<f:validateLength maximum="80" />
				</h:inputTextarea>
				<h:message for="add"></h:message>


				<h:outputText value="Phone No:" />
				<h:inputText id="phone" value="#{bean.employeeTO.phoneNo}"
					required="true" requiredMessage="Phone Number is mandatory"
					converterMessage="Only digits are allowed in Phone Number"
					validatorMessage="Should contain maximum of 12 digits (including country code and local code)">
					<f:validateLength maximum="12" />
				</h:inputText>
				<h:message for="phone"></h:message>


				<h:outputText value="Designation:" />
				<h:inputText id="des" value="#{bean.employeeTO.designation}"
					required="true" requiredMessage="Designation is mandatory">
					<f:validator validatorId="desDocVal" />
				</h:inputText>
				<h:message for="des"></h:message>


				<h:outputText value="Department:" />
				<h:inputText id="dept" value="#{bean.employeeTO.department}"
					required="true" requiredMessage="Department is mandatory">

				</h:inputText>
				<h:message for="dept"></h:message>


				<h:outputText value="Consultation Fees:" />
				<h:inputText id="conf" value="#{bean.employeeTO.consultationFees}"
					required="true" requiredMessage="Consultation Fees is mandatory"
					converterMessage="Enter only numbers"
					validatorMessage="Cannot be more than 4 digits">
					<f:validateLength minimum="1" maximum="4" />
					<f:validateDoubleRange minimum="1" maximum="9999"></f:validateDoubleRange>
				</h:inputText>
				<h:message for="conf"></h:message>


				<h:outputText value="Slot1:" />
				<h:selectBooleanCheckbox id="sl1" value="#{bean.employeeTO.s1}" />
				<h:message for="sl1"></h:message>


				<h:outputText value="Slot2:" />
				<h:selectBooleanCheckbox id="sl2" value="#{bean.employeeTO.s2}" />
				<h:message for="sl2"></h:message>


				<h:outputText value="Slot3:" />
				<h:selectBooleanCheckbox id="sl3" value="#{bean.employeeTO.s3}" />
				<h:message for="sl3"></h:message>
			</h:panelGrid>
			<br>
			<h:commandButton value="Add Employee" action="#{bean.addDoctor}"></h:commandButton>
		</h:form>
	</c:if>


	<c:if test="${bean.select==\"Receptionist\"}">
		<h:form id="form1">
			<h:panelGrid border="1" columns="3">
				<h:outputText value="Employee Name:"></h:outputText>
				<h:inputText id="rname" value="#{bean.employeeTO.employeeName}"
					required="true" requiredMessage="Employee Name is mandatory">
					<f:validator validatorId="nVal" />
				</h:inputText>
				<h:message for="rname"></h:message>

				<h:outputText value="Date Of Joining:"></h:outputText>
				<h:panelGroup>
					<h:inputText id="date" value="#{bean.employeeTO.dateOfJoining}"
						required="true" requiredMessage="Date Of Joining is mandatory"
						converterMessage="Invalid Date Format">
						<f:convertDateTime pattern="dd-MMM-yyyy" />
					</h:inputText>
					<a onclick="dateFun();"> <img src="cal.gif" width="16"
						height="16" border="0" alt="Pick a Date"></a>
				</h:panelGroup>
				<h:message for="date"></h:message>

				<h:outputText value="Qualification:"></h:outputText>
				<h:inputText id="rqual" value="#{bean.employeeTO.qualification}"
					required="true" requiredMessage="Qualification is mandatory">
					<f:validator validatorId="qVal" />
				</h:inputText>
				<h:message for="rqual"></h:message>

				<h:outputText value="Address:"></h:outputText>
				<h:inputTextarea rows="3" id="radd"
					value="#{bean.employeeTO.address}" required="true"
					requiredMessage="Address is mandatory"
					validatorMessage="Cannot have more than 80 characters">
					<f:validateLength maximum="80" />

				</h:inputTextarea>
				<h:message for="radd"></h:message>

				<h:outputText value="PhoneNo:"></h:outputText>
				<h:inputText id="rphone" value="#{bean.employeeTO.phoneNo}"
					required="true" requiredMessage="Phone Number is mandatory"
					converterMessage="Only digits are allowed in Phone Number"
					validatorMessage="Should contain maximum of 12 digits (including country code and local code)">
					<f:validateLength maximum="12" />
				</h:inputText>
				<h:message for="rphone"></h:message>

				<h:outputText value="Designation"></h:outputText>
				<h:inputText id="rdes" value="#{bean.employeeTO.designation}"
					required="true" requiredMessage="Designation is mandatory">
					<f:validator validatorId="desRecVal" />
				</h:inputText>
				<h:message for="rdes"></h:message>
			</h:panelGrid>
			<br>
			<h:commandButton value="Add Employee"
				action="#{bean.addReceptionist}"></h:commandButton>
		</h:form>
	</c:if>

	<br>
	<h:outputText value="#{bean.message}"></h:outputText>
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