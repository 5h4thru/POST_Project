<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>BioMedical Management</title>
<link href="style1.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="datetimepicker.js"></script>
<script type="text/javascript">
	function pickdate()
{
		new NewCal('form1:dateofpurchase','ddMMMyyyy');
}
</script>
</head>
<body>
<center>
<img src="hospitalheader.jpg" width="80%" style="height: 100">
<f:view>
<c:choose>
<c:when test="${srole=='AD'}">
<h:form id="form">
<div id="nav-menu">
<h:panelGrid border="0" columns="7" cellspacing="10">
		<h:outputLink style="font-size:14;" value="EmployeeManagement.jsp">Employee Management</h:outputLink>
		<h:outputLink style="font-size:14;" value="DrugManagement.jsp">Drug Management</h:outputLink>
		<h:outputLink style="font-size:14;" value="WardManagement.jsp">Ward Management</h:outputLink>
		<h:outputLink style="font-size:14;" value="EquipmentManagement.jsp">Equipment Management</h:outputLink>
		<h:outputLink value="PurchaseMedicine.jsp">Purchase Medicine</h:outputLink>
		<h:outputLink style="font-size:14;" value="ApproveRequest.jsp">Approve Request</h:outputLink>
		<h:outputLink style="font-size:14;" value="Home.jsp">Home</h:outputLink>
</h:panelGrid>
</div>
<br>
<h3>Bio Medical Management</h3>

<h:panelGrid border="0" columns="3" cellpadding="10">
	
	<h:commandLink action="#{bioMedicalEquipmentBean.navigate}">Add new Equipments.
	
	</h:commandLink>

	<h:commandLink action="#{bioMedicalEquipmentBean.navigate1}">Delete Existing Equipments.
	
	</h:commandLink>
	
	<h:commandLink action="#{bioMedicalEquipmentBean.navigate2}">Modify Equipments.
	
	</h:commandLink>
	
	</h:panelGrid>

</h:form>



<h:form id="form1">
<c:if test="${bioMedicalEquipmentBean.status=='add'}">

Addition Form<br>
			<h:panelGrid border="1" columns="2" style="border-collapse:collapse" >
				<h:outputText value="Equipment Name."></h:outputText>
				<h:inputText id="name" value="#{bioMedicalEquipmentBean.equipmentTO.equipmentName}" required="true" requiredMessage="Equipment Name Required!!">
				<f:validator validatorId="namevalidator"/>
				</h:inputText>
				
				<h:outputText value="Date Of Purchase."></h:outputText>
				<h:panelGroup>
				<h:inputText id="dateofpurchase" value="#{bioMedicalEquipmentBean.equipmentTO.dateOfPurchase}" required="true" requiredMessage="Date Required!!" converterMessage="Enter the Date in dd-MMM-yyyy format">
				<f:convertDateTime pattern="dd-MMM-yyyy" />
				<f:validator validatorId="date"/>
				</h:inputText>
				<a onclick="pickdate()" ><img alt="Select Date" src="cal.gif" ></a>
				</h:panelGroup>
				
				<h:outputText value="Cost."></h:outputText>
				<h:inputText id="cost" value="#{bioMedicalEquipmentBean.equipmentTO.cost}" required="true" requiredMessage="Cost Required!!" validatorMessage="Cost cannot be less than or equal to zero and should not be more than 5 digits" converterMessage="Cost Can only be Positive Integer">
				<f:validateLongRange minimum="1"></f:validateLongRange>
				</h:inputText>
				
				<h:outputText value="Quantity."></h:outputText>
				<h:inputText id="quantity" value="#{bioMedicalEquipmentBean.equipmentTO.quantity}" required="true" requiredMessage="Quantity Required!!" validatorMessage="Quantity cannot be less than or equal to Zero and  should not be more than 5 digits" converterMessage="Quantity Can only be Positive Integer">
				<f:validateLongRange minimum="1"></f:validateLongRange>
				</h:inputText>
				
				
				
			
				
			</h:panelGrid><br><br>
			<h:commandButton value="Add Equipment" action="#{bioMedicalEquipmentBean.addEquipment}">
			</h:commandButton>
			
			<br><br>
			<h:messages></h:messages>
			<h:outputText value="#{bioMedicalEquipmentBean.message}"></h:outputText>
</c:if>
</h:form>


<h:form>
<c:if test="${bioMedicalEquipmentBean.status=='del'}">
Deletion Form
<br><br>
	<h:panelGrid border="1" columns="2">
			<h:outputText value="Equipment Name:"></h:outputText>
			<h:selectOneMenu id="selectequip" value="#{bioMedicalEquipmentBean.equipmentNo}" required="true" requiredMessage="Please select a Drug!!" >
			<f:selectItem itemLabel="Select" itemValue="0"/>
			<f:selectItems value="#{bioMedicalEquipmentBean.idList}"/>
			</h:selectOneMenu>
		</h:panelGrid>
		<br>
		<h:commandButton value="Delete Equipment" action="#{bioMedicalEquipmentBean.delEquipment}">
		
		</h:commandButton><br><br>
		<h:messages></h:messages>
		<h:outputText value="#{bioMedicalEquipmentBean.message}"></h:outputText>
</c:if>
</h:form>

<h:form>

<c:if test="${bioMedicalEquipmentBean.status=='modify'}">
Modification Form
<br>
<br>
	<h:panelGrid border="1" columns="2">
			<h:outputText value="Equipment Name:" ></h:outputText>
			<h:selectOneMenu value="#{bioMedicalEquipmentBean.equipmentNo}" valueChangeListener="#{bioMedicalEquipmentBean.getTO}" onchange="submit()" required="true" requiredMessage="Please Select a equipment">
			<f:selectItem itemLabel="Select" itemValue="0"/>
			<f:selectItems value="#{bioMedicalEquipmentBean.idList}"/>
			
			</h:selectOneMenu>
		</h:panelGrid>
		<br>
		<br>
	<c:if test="${ not empty bioMedicalEquipmentBean.equipmentTO1}">
			<h:dataTable border="1" value="#{bioMedicalEquipmentBean.equipmentTO1}" var="row">
				<h:column id="column1">
					<f:facet name="header">
						<h:outputText value="Equipment Name"></h:outputText>
					</f:facet>
					<h:inputText id="EquipmentName" value="#{row.equipmentName}" converterMessage="Name Can contain only spaces and Characters" required="true" requiredMessage="Equipment Name is required!!">
					<f:validator validatorId="namevalidator" />
					</h:inputText>
				</h:column>
				<h:column id="column2">
					<f:facet name="header">
						<h:outputText value="Date Of Purchase"></h:outputText>
					</f:facet>
					<h:inputText id="dateofpurchase" value="#{row.dateOfPurchase}" required="true" requiredMessage="Date of Purchase is Required!!">
					<f:convertDateTime type="date" pattern="dd-MMM-yyyy"/>
					<f:validator validatorId="date"/>
					
					</h:inputText>
				</h:column>
				<h:column id="column3">
					<f:facet name="header">
						<h:outputText value="Cost."></h:outputText>
					</f:facet>
				<h:inputText id="cost" value="#{row.cost}" required="true" requiredMessage="Cost is Required!!" converterMessage="Cost Should be a Positive Integer" validatorMessage="Cost cannot be less than or equal to 0,Should not exceed 5 digits">
				<f:validateLongRange minimum="1" maximum="99999"></f:validateLongRange>
				</h:inputText>
				</h:column>
				<h:column id="column4">
					<f:facet name="header">
						<h:outputText value="Quantity."></h:outputText>
					</f:facet>
				<h:inputText id="quantity" value="#{row.quantity}" required="true" requiredMessage="Quantity is Required!!" converterMessage="Quantity Should be a Positive Integer" validatorMessage="Quantity cannot be less than or equal to zero,should not exceed 5 digits" > 
				<f:validateLongRange minimum="1" maximum="99999"></f:validateLongRange>
				</h:inputText>
				</h:column>
				
			</h:dataTable>
			
			</c:if>
	<br>
	<h:commandButton value="Update" action="#{bioMedicalEquipmentBean.updateEqp}" >
	
	</h:commandButton>

	<br>
	<br>
	
	<h:messages id="value" globalOnly="false" ></h:messages>
	<h:outputText value="#{bioMedicalEquipmentBean.message}"></h:outputText>
	

</c:if>
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