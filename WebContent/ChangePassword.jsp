<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<link rel="Stylesheet" href="style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password</title>
</head>
<body>
<f:view>

<center>
<h:form>
<img src="hospitalheader.jpg"><br><br><br><br>
<h2>Change Password</h2><br>
<h4>

<h:panelGrid border="1" columns="2">

		<h:outputText value="Enter Your UserName"/>
		
		<h:inputText value="#{passwordRecoveryBean.userName}">
			<f:validator validatorId="nv"></f:validator>
		</h:inputText>
		
		<h:outputText value="Enter Your Old Password"/>

			<h:inputSecret value="#{passwordRecoveryBean.password}"></h:inputSecret>
		
	</h:panelGrid>
	<br><br>
	<h:commandButton value="Submit" action="#{passwordRecoveryBean.validateLogin}"></h:commandButton>
	<br>
		
		<c:if test="${passwordRecoveryBean.check==true && passwordRecoveryBean.visibleConfirm==false}">
		<h:panelGrid border="1" columns="2">
		
		
			<h:outputText value="New Password"></h:outputText>
			<h:inputSecret value="#{passwordRecoveryBean.newPassword}" valueChangeListener="#{passwordRecoveryBean.showConfirmPassword}" onchange="submit()" required="true" requiredMessage="New Password is Mandatory!!!">
			<f:validator validatorId="pv"></f:validator>
			</h:inputSecret>
			<h:outputText value=""></h:outputText>
			<h:commandButton value="Change Password" action="#{passwordRecoveryBean.updatePassword}"></h:commandButton>
		</h:panelGrid>
		</c:if>
		
		
		<c:if test="${passwordRecoveryBean.check==true &&  passwordRecoveryBean.visibleConfirm==true}">
		<h:panelGrid border="1" columns="2">
			<h:outputText value="New Password"></h:outputText>
			<h:inputSecret value="#{passwordRecoveryBean.newPassword}" readonly="true">
			<f:validator validatorId="pv"></f:validator>
			</h:inputSecret>
			<h:outputText value="Re-enter Password"></h:outputText>
			<h:inputSecret value="#{passwordRecoveryBean.confirmPassword}" required="true" requiredMessage="Re-enter Password is Mandatory!!!"></h:inputSecret>
			<h:outputText value=""></h:outputText>
			<h:commandButton value="Change Password" action="#{passwordRecoveryBean.updatePassword}"></h:commandButton>
		</h:panelGrid>
		</c:if>
		<br>
	
	
	
	
	<h:outputLink value="Login.jsp">login</h:outputLink><br>
	</h4>
	<h:messages></h:messages>
	<h:outputText value="#{passwordRecoveryBean.message}"></h:outputText>
	
</h:form>
</center>
</f:view>
</body>
</html>