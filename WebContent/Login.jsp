<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Login Page</title>
</head>
<body>
<f:view>
<center>
<img src="hospitalheader.jpg" width="700"/>
<h:form>
<br>
<br>
<br>
<h2>Login</h2><br>
Username<h:inputText value="#{loginBean.userName}" id="username" required="true" requiredMessage="Username is Mandatory">
<f:validator validatorId="nv"/>
</h:inputText><br>
Password<h:inputSecret value="#{loginBean.password}" id="password" required="true" requiredMessage="Password is Mandatory"></h:inputSecret><br><br>
<h:commandButton value="Login" action="#{loginBean.validateLogin}"></h:commandButton>
<br><br>
<h:outputLink value="ChangePassword.jsp">Change Password</h:outputLink><br>
<h:message for="username"></h:message><br>
<h:message for="password"></h:message><br>
<h:outputText value="#{loginBean.message}"></h:outputText>
</h:form>
</center>

</f:view>
</body>
</html>