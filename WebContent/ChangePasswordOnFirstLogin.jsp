<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="Stylesheet" href="style.css" type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Password First Login</title>
</head>
<body>
<f:view>
<img src="hospitalheader.jpg">
<c:choose>
<c:when test="${srole=='PA' || srole=='RE' || srole=='AD' || srole=='DC'}">
<center>
<h2>Change Password</h2>
</center>
Logged in as <c:out value="${uname}">
		
	</c:out>
<center>
<h:form>
<h:panelGrid border="1" columns="2">
			<h:outputText value="Password(*)"></h:outputText>
			<h:inputSecret value="#{loginBean.password}" required="true" requiredMessage="Password is Mandatory!!!">
				<f:validator validatorId="pv"></f:validator>
			</h:inputSecret>
			<h:outputText value="Confirm Password(*)"></h:outputText>
			<h:inputSecret value="#{loginBean.confirmedPassword}" required="true" requiredMessage="Password is Mandatory!!!"></h:inputSecret>
		</h:panelGrid>
		
		<h:commandButton value="Save Changes" action="#{loginBean.updateLogin}"></h:commandButton>
		<h:commandButton value="Reset" type="reset"></h:commandButton><br><br>
		<h:outputText value="#{loginBean.message}"></h:outputText><br><br>
		<h:outputLink value="Login.jsp">login</h:outputLink><br>
	<h:messages></h:messages>
	
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