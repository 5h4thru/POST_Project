<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ward Management</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<div id="header"></div><br>

<CENTER>
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
</h:panelGrid><br>
<h:form>

<h:panelGrid columns="3" cellpadding="3" cellspacing="10">
<h:commandLink  actionListener="#{wardBean.getlink}" id="add" value="Add New Wards." ><u></u></h:commandLink>
<h:commandLink  actionListener="#{wardBean.getlink}" id="delete" value="Delete Existing Wards." ><u></u></h:commandLink>
<h:commandLink  actionListener="#{wardBean.getlink}" id="modify" value="Modify Wards." ><u></u></h:commandLink>
</h:panelGrid>
</h:form>

<h:form>
<c:if test="${wardBean.status==1}">
<h:outputLabel>Addition Form</h:outputLabel>
<h:panelGrid border="2" columns="2" >
<h:outputLabel value="Ward Type."></h:outputLabel>
<h:selectOneRadio value="#{wardBean.wardTO.WType}" required="true" requiredMessage="WardType is mandatory" id="id1">
<f:selectItem itemValue="GEN" itemLabel="General"/>
<f:selectItem itemValue="PRI" itemLabel="Private"/>
</h:selectOneRadio>
<h:outputLabel value="No Of Beds."></h:outputLabel>
<h:inputText value="#{wardBean.wardTO.noOfBeds}" validator="#{wardBean.noofbedsvalidate}" required="true" requiredMessage="No of beds is mandatory" converterMessage="No of beds can be only Numbers" id="id2">
</h:inputText>
<h:outputLabel value="Price."></h:outputLabel>
<h:inputText value="#{wardBean.wardTO.WPrice}"  validator="#{wardBean.pricevalidate}" required="true" converterMessage="Price can be only Numbers" requiredMessage="Price is mandatory" id="id3">
</h:inputText>
</h:panelGrid>
<h:commandButton value="Add Ward" action="#{wardBean.addWard}"><br>
<h:outputText value="#{wardBean.message}"> </h:outputText>
<h:message for="id1"></h:message><br>
<h:message for="id2"></h:message><br>
<h:message for="id3"></h:message><br>
</h:commandButton>
</c:if>
</h:form>

<h:form>
<c:if test="${wardBean.status==2}">
<h:outputLabel>Deletion Form</h:outputLabel>
<h:panelGrid border="2" columns="2">
<h:outputLabel value="Ward No."></h:outputLabel>
<h:selectOneMenu value="#{wardBean.wardNo}" required="true" requiredMessage="Please select Ward No to be deleted" id="id4" >
<f:selectItem itemLabel="--Select--" itemValue="0" />
<f:selectItems value="#{wardBean.idList}"/>
</h:selectOneMenu>
</h:panelGrid>
<h:commandButton value="Delete Ward" action="#{wardBean.delWard}"><br>
<h:outputText value="#{wardBean.message}"> </h:outputText>
</h:commandButton>
<h:message for="id4"></h:message>
</c:if>
</h:form>

<c:if test="${wardBean.status==3}">
<h:outputLabel>Modification Form</h:outputLabel>
<h:form>
<h:panelGrid border="2" columns="2">
<h:outputLabel value="Ward No."></h:outputLabel>
<h:selectOneMenu value="#{wardBean.wardNo}" required="true" requiredMessage="Please select Ward No to be deleted" id="id4"  onchange="submit()" valueChangeListener="#{wardBean.getTO}">
<f:selectItem itemLabel="--Select--" itemValue="0"/>
<f:selectItems value="#{wardBean.idList}"/>
</h:selectOneMenu>
</h:panelGrid>
</h:form>

<c:if test="${wardBean.mstatus==1}">
<h:form>
<h:panelGrid border="2" columns="4">
<h:outputLabel value="Ward Type."></h:outputLabel>
<h:outputLabel value="Ward Status."></h:outputLabel>
<h:outputLabel value="No Of Beds."></h:outputLabel>
<h:outputLabel value="Price"></h:outputLabel>
<h:inputText value="#{wardBean.wardTO.WType}" validator="#{wardBean.wardtypevalidate}" id="id7"  required="true" requiredMessage="WardType Cannot Be Empty" >
</h:inputText>
<h:inputText value="#{wardBean.wardTO.WStatus}" validator="#{wardBean.wardstatusvalidate}" id="id8"  required="true" requiredMessage="WardStatus Cannot Be Empty">
</h:inputText>
<h:inputText value="#{wardBean.wardTO.noOfBeds}" validator="#{wardBean.noofbedsvalidate}" converterMessage="No of Beds can be only Numbers"  id="id9" required="true"  requiredMessage="No of beds Cannot Be Empty">
</h:inputText>
<h:inputText value="#{wardBean.wardTO.WPrice}" validator="#{wardBean.pricevalidate}" converterMessage="Price can be only Numbers"  required="true" requiredMessage="Price Cannot Be Empty" id="id10">
</h:inputText>
</h:panelGrid>
<h:commandButton value="Update Ward" action="#{wardBean.updateWard}"><br>
<h:message for="id7"></h:message><br>
<h:message for="id8"></h:message><br>
<h:message for="id9"></h:message><br>
<h:message for="id10"></h:message><br>
</h:commandButton>
</h:form>
</c:if>
<h:outputText value="#{wardBean.message}"></h:outputText>
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
</CENTER>
</body>
</html>


