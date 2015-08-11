<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Bill Generation</title>
</head>
<body>
<f:view>
<img src="hospitalheader.jpg" width="700"/>
<c:choose>
<c:when test="${srole=='RE'}">
<center>
<table cellpadding="20" id=nav-menu>
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
<h2>Receipt</h2><br>
<table border="1">
                  <tr>
                        <td>
                        <table>
                              <tr>
                                    <td><h:outputText value="Bill No"></h:outputText></td>
                                    <td><h:outputText value="Patient Id."></h:outputText></td>
                                    <td><h:outputText value="Registration No."></h:outputText></td>
                                    <td><h:outputText value="Date."></h:outputText></td>
                              </tr>
                              <tr>
                                    <td><h:outputText value="#{oPDRegistrationBean.billNo}"></h:outputText>
                                    </td>
                                    <td><h:outputText value="#{oPDRegistrationBean.opdAppointmentTO.patientId}"></h:outputText>
                                    </td>
                                    <td><h:outputText value="#{oPDRegistrationBean.regNO}"></h:outputText>
                                    </td>
                                    <td><h:outputText value="#{oPDRegistrationBean.billingTO.billingDate}">
                                    <f:convertDateTime pattern="dd-MMM-yyyy"/>
                                    </h:outputText>
                                    </td>
                              </tr>
                        </table>
                        <hr>
                        <table>
                              <tr>
                                    <td><h:outputText value="Description"></h:outputText></td>
                                    <td><h:outputText value="Amount"></h:outputText></td>
                              </tr>
                              <tr>
                                    <td><h:outputText value="#{oPDRegistrationBean.billingTO.description}"></h:outputText></td>
                                    <td><h:outputText value="#{oPDRegistrationBean.billingTO.amount}"></h:outputText></td>
                              </tr>
                        </table>
                        <hr>
                        <table>
                              <tr>
                                    <td align="center"><h:outputText value="Total"></h:outputText></td>
                              </tr>
                              <tr>
                                    <td align="center"><h:outputText value="#{oPDRegistrationBean.billingTO.amount}"></h:outputText></td>
                              </tr>
                        </table>
                        
                        </td>
                  </tr>
            </table><br><br>
           <h:outputText value="#{oPDRegistrationBean.message}"></h:outputText>

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