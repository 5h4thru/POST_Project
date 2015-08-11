<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="style.css" style="text/css">
<title>View Schedule</title>
</head>
<body>
<CENTER><img src="hospitalheader.jpg"></center>
<f:view>




	<c:choose>
		<c:when test="${srole=='RE'}">

			<center><h:form>
Logged in as <c:out value="${uname}"></c:out>
				<table cellpadding="20" id="nav-menu">

					<tr>
						<td><h:outputLink value="TariffEnquiry.jsp">Tariff Enquiry</h:outputLink></td>
						<td><h:outputLink value="InpatientEnquiry.jsp">InPatientEnquiry</h:outputLink></td>
						<td><h:outputLink value="BillingAndPayment.jsp">Billing</h:outputLink></td>
						<td><h:outputLink value="ViewSchedule.jsp">View Doctors Schedule</h:outputLink></td>
						<td><h:outputLink value="IPDRegistration.jsp">In-Patient Registration</h:outputLink></td>
						<td><h:outputLink value="OPDRegistration.jsp">Out-Patient Registration</h:outputLink></td>
						<td><h:outputLink value="Home.jsp">Home</h:outputLink></td>
					</tr>
				</table>



				<h:selectOneRadio value="#{vbean.select}" onclick="submit();">
					<f:selectItem itemValue="doctor" itemLabel="View By Doctor Name" />
					<f:selectItem itemValue="special"
						itemLabel="View By Specialization" />
				</h:selectOneRadio>
			</h:form> <h:form>

				<c:if test="${vbean.select==\"doctor\"}">
					<br>
					<br>
					<h:panelGrid columns="2" border="1">
						<h:outputText value="Select a doctor name" />
						<h:selectOneMenu id="doc" required="true"
							requiredMessage="Please select a doctor Name"
							value="#{vbean.doctorName}">
							<f:selectItem itemLabel="--select--" />
							<f:selectItems value="#{vbean.doctorNames}" />
						</h:selectOneMenu>
					</h:panelGrid>
					<br>
					<h:commandButton value="View Schedule"
						action="#{vbean.getDoctorSchedule}"></h:commandButton>
					<br>
					<h:message for="doc"></h:message>
					<c:if test="${not empty vbean.dto}">
						<br>
						<h:dataTable border="2" value="#{vbean.dto}" var="row">
							<h:column>
								<f:facet name="header">
									<h:outputText value="Doctor Reference ID" />
								</f:facet>
								<h:outputText value="#{row.userName}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Specialization" />
								</f:facet>
								<h:outputText value="#{row.department}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Slot 1" />
								</f:facet>
								<h:outputText value="#{row.slot1}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Slot 2" />
								</f:facet>
								<h:outputText value="#{row.slot2}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Slot 3" />
								</f:facet>
								<h:outputText value="#{row.slot3}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Consultation Fees" />
								</f:facet>
								<h:outputText value="#{row.consultationFees}" />
							</h:column>
						</h:dataTable>
					</c:if>

				</c:if>

				<c:if test="${vbean.select==\"special\"}">
					<br>
					<br>
					<h:panelGrid columns="2">
						<h:outputText value="Select a specialization" />
						<h:selectOneMenu id="spe" required="true"
							requiredMessage="Please select a specialization"
							value="#{vbean.specialization}">
							<f:selectItem itemLabel="--select--" />
							<f:selectItems value="#{vbean.specializationNames}" />
						</h:selectOneMenu>
					</h:panelGrid>
					<br>
					<h:commandButton value="View Schedule"
						action="#{vbean.getSpecialSchedule}"></h:commandButton>
					<br>
					<h:message for="spe"></h:message>
					<c:if test="${not empty vbean.dto}">
						<br>
						<h:dataTable border="2" value="#{vbean.dto}" var="row">
							<h:column>
								<f:facet name="header">
									<h:outputText value="Doctor Reference ID" />
								</f:facet>
								<h:outputText value="#{row.userName}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Specialization" />
								</f:facet>
								<h:outputText value="#{row.department}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Slot 1" />
								</f:facet>
								<h:outputText value="#{row.slot1}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Slot 2" />
								</f:facet>
								<h:outputText value="#{row.slot2}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Slot 3" />
								</f:facet>
								<h:outputText value="#{row.slot3}" />
							</h:column>

							<h:column>
								<f:facet name="header">
									<h:outputText value="Consultation Fees" />
								</f:facet>
								<h:outputText value="#{row.consultationFees}" />
							</h:column>
						</h:dataTable>
					</c:if>

				</c:if>
			</h:form></center>
		</c:when>
		<c:otherwise>
			<center>
			<h2><h:outputText value="Access Denied. Login to continue"></h:outputText></h2>
			<br>
			<br>
			<h:outputLink value="Login.jsp">Login</h:outputLink></center>
		</c:otherwise>
	</c:choose>



</f:view>
</body>
</html>