<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Drug Management</title>
<link href="style1.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="datetimepicker.js"></script>
<script type="text/javascript">
	function pickdate()
{
		new NewCal('form1:date','ddMMMyyyy');
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
	<h3>Drug Management</h3>
	
	<h:panelGrid border="0" columns="3" cellpadding="10">
	
	<h:commandLink action="#{drugBean.navigate}">Add new Drugs.
	
	</h:commandLink>

	<h:commandLink action="#{drugBean.navigate1}">Delete Existing Drugs.
	
	</h:commandLink>
	
	<h:commandLink action="#{drugBean.navigate2}">Modify Drugs.
	
	</h:commandLink>
	
	</h:panelGrid>
	</h:form >
	<h:form id="form1">

	<c:if test="${drugBean.status=='add'}">


			Addition Form
			<h:panelGrid border="1" columns="2" style="border-collapse:collapse" >
				
				<h:outputText value="Drug Name."></h:outputText>
				<h:inputText id="drugname" value="#{drugBean.drugTO.drugName}" required="true" requiredMessage="Drug Name Required!!">
				<f:validator validatorId="namevalidator"/>
				</h:inputText>
				
				
				
				<h:outputText value="Date Of Expiry."></h:outputText>
				<h:panelGroup>
				<h:inputText id="date" value="#{drugBean.drugTO.dateOfExpiry}" required="true" requiredMessage="Date Of Expiry Required!!" converterMessage="Enter the Date Of Expiry in dd-MMM-yyyy format">
				<f:convertDateTime pattern="dd-MMM-yyyy" />
				<f:validator validatorId="date"/>
				</h:inputText>
				<a onclick="pickdate()" ><img alt="Select Date" src="cal.gif" ></a>
				</h:panelGroup>
				
				
				<h:outputText value="Cost Per Unit."></h:outputText>
				<h:inputText id="cost" value="#{drugBean.drugTO.costPerUnit}" required="true" requiredMessage="Cost Required!!" validatorMessage="Cost cannot be less than or equal to zero and should not be more than 5 digits" converterMessage="Cost Can only be Positive Integer">
				<f:validateLongRange minimum="1" maximum="99999"></f:validateLongRange>
				</h:inputText>
				
				
				<h:outputText value="Quantity."></h:outputText>
				<h:inputText id="quantity" value="#{drugBean.drugTO.quantity}" required="true" requiredMessage="Quantity Required!!" validatorMessage="Quantity cannot be less than or equal to Zero and should not be more than 5 digits" converterMessage="Quantity Can only be Positive Integer">
				<f:validateLongRange minimum="1" maximum="99999"></f:validateLongRange>
				</h:inputText>
				
				
				<h:outputText value="Company."></h:outputText>
				<h:inputText id="company" value="#{drugBean.drugTO.company}" required="true" requiredMessage="Company Required!!" >
				<f:validator validatorId="namevalidator"/>
				</h:inputText>
			</h:panelGrid><br><br>	
				
			
				
			
			<h:commandButton value="Add Drug" action="#{drugBean.addDrug}" >
			</h:commandButton>
			
			<br><br>
			<h:messages></h:messages>
			<h:outputText value="#{drugBean.message}"></h:outputText>

			
			</c:if>
	
	<br>
</h:form>
	<h:form>
	<c:if test="${drugBean.status=='del'}">
	Deletion Form
<br><br>
	<h:panelGrid border="1" columns="2">
			<h:outputText value="Drug Name:"></h:outputText>
			<h:selectOneMenu id="selectdrug" value="#{drugBean.drugNo}" required="true" requiredMessage="Please select a Drug!!">
			<f:selectItem itemLabel="Select" itemValue="0"/>
			<f:selectItems value="#{drugBean.idList}"/>
			</h:selectOneMenu>
		</h:panelGrid>
		<br>
		
		<br>
		<h:commandButton value="Delete Drug" action="#{drugBean.delDrug}">
		
		
		</h:commandButton><br><br>
		<h:message for="selectdrug"></h:message>
		<h:outputText value="#{drugBean.message}"></h:outputText>
	</c:if>	
	</h:form>
	
<h:form>

	<c:if test="${drugBean.status=='modify'}">

	<br>Modification Form
	
<br><br>
	<h:panelGrid border="1" columns="2">
			<h:outputText value="Drug Name:" ></h:outputText>
			<h:selectOneMenu id="selectdrugm" value="#{drugBean.drugNo}" valueChangeListener="#{drugBean.getTO}" onchange="submit()" required="true" requiredMessage="Please select a Drug!!" >
			<f:selectItem itemLabel="Select" itemValue="0"/>
			<f:selectItems value="#{drugBean.idList}"/>
			
			</h:selectOneMenu>
			
		</h:panelGrid>
		
		<br>
	
	
	<c:if test="${not empty drugBean.listDrugTO}">
			
			<h:dataTable border="1" value="#{drugBean.listDrugTO}" var="row">
				<h:column id="column1">
					<f:facet name="header">
						<h:outputText value="DrugName"></h:outputText>
					</f:facet>
					<h:inputText id="drugname" value="#{row.drugName}" converterMessage="Name Can contain only spaces and Characters" required="true" requiredMessage="Drug name is required!!">
					<f:validator validatorId="namevalidator" />
				
					</h:inputText>
				</h:column>
				<h:column id="column2">
					<f:facet name="header">
						<h:outputText value="Date Of Expiry"></h:outputText>
					</f:facet>
					<h:outputText id="date" value="#{row.dateOfExpiry}" rendered="true">
					<f:convertDateTime pattern="dd-MMM-yyyy" />
					</h:outputText>
				</h:column>
				<h:column id="column3">
					<f:facet name="header">
						<h:outputText value="Cost Per Unit.">
						</h:outputText>
					</f:facet>
				<h:inputText id="cost" value="#{row.costPerUnit}" converterMessage="Cost Should be a Positive Integer (Eg:23)" required="true" requiredMessage="Cost is Required!!" validatorMessage="Cost cannot be less than or equal to Zero and should not be more than 5 digits">
				<f:validateLongRange minimum="0"></f:validateLongRange>
				</h:inputText>
				</h:column>
				<h:column id="column4">
					<f:facet name="header">
						<h:outputText value="Quantity.">
						
						</h:outputText>
					</f:facet>
				<h:inputText id="quantity" value="#{row.quantity}" converterMessage="Quantity Should be a Positive Integer (Eg:23)"  required="true" requiredMessage="Quantity is Required!!" validatorMessage="Quantity cannot be less than or equal to Zero and should not be more than 5 digits">
				<f:validateLongRange minimum="0"></f:validateLongRange>
				</h:inputText>
				</h:column>
				<h:column id="column5">
					<f:facet name="header">
						<h:outputText value="Company."></h:outputText>
					</f:facet>
				<h:inputText id="company" value="#{row.company}" converterMessage="Name Can contain only spaces and characters"  required="true" requiredMessage="Company is Required!!">
				<f:validator validatorId="namevalidator"/>
				</h:inputText>
				</h:column>
			</h:dataTable>
			
		</c:if>
	<br>
	<h:commandButton value="Update" action="#{drugBean.updateDrug}"></h:commandButton>
	<br>

	
	<h:messages></h:messages>
	
	
	<h:outputText value="#{drugBean.message}"></h:outputText>
	
	

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