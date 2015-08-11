package infy.com.hospital.managedbean;

import infy.com.hospital.manager.DischargeSummaryManager;
import infy.com.hospital.to.DischargeTO;

import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class DischargeSummaryBean {
private String registrationNo;
private String userName;
private String doctorId;
private int bedNo;
private String wardNo;
private Date dateOfAdmission;
private Date dateOfDischarge;
private String department;

private String reasonForAdmission;
private String admissionStatus;
private String message;
FacesContext facesContext;
ExternalContext externalContext;
HttpSession session;

public DischargeSummaryBean() {
	try
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(true);
  
      
        this.registrationNo=(String)session.getAttribute("s_regNo");
        this.message=(String)session.getAttribute("s_msg");
		DischargeTO dto=new DischargeSummaryManager().dischargeSummary(registrationNo);
		//DischargeTO dto=new DischargeSummaryManager().DischargeSummary("6001");
		this.admissionStatus = dto.getAdmissionStatus();
		this.bedNo = dto.getBedNo();
		this.dateOfAdmission = dto.getDateOfAdmission();
		this.dateOfDischarge = dto.getDateOfDischarge();
		this.doctorId = dto.getDoctorId();
		this.reasonForAdmission = dto.getReasonForAdmission();
		this.registrationNo = dto.getRegistrationNo();
		this.userName = dto.getUsername();
		this.wardNo = dto.getWardNo();
		this.department = dto.getDepartment();
	}
	catch (Exception e)
	{
		this.message = e.getMessage();
	}
	
	
}
public String getDepartment() {
	return department;
}

public void setDepartment(String department) {
	this.department = department;
}

public String getRegistrationNo() {
	return registrationNo;
}
public void setRegistrationNo(String registrationNo) {
	this.registrationNo = registrationNo;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getDoctorId() {
	return doctorId;
}
public void setDoctorId(String doctorId) {
	this.doctorId = doctorId;
}
public int getBedNo() {
	return bedNo;
}
public void setBedNo(int bedNo) {
	this.bedNo = bedNo;
}
public String getWardNo() {
	return wardNo;
}
public void setWardNo(String wardNo) {
	this.wardNo = wardNo;
}
public Date getDateOfAdmission() {
	return dateOfAdmission;
}
public void setDateOfAdmission(Date dateOfAdmission) {
	this.dateOfAdmission = dateOfAdmission;
}
public Date getDateOfDischarge() {
	return dateOfDischarge;
}
public void setDateOfDischarge(Date dateOfDischarge) {
	this.dateOfDischarge = dateOfDischarge;
}
public String getReasonForAdmission() {
	return reasonForAdmission;
}
public void setReasonForAdmission(String reasonForAdmission) {
	this.reasonForAdmission = reasonForAdmission;
}
public String getAdmissionStatus() {
	return admissionStatus;
}
public void setAdmissionStatus(String admissionStatus) {
	this.admissionStatus = admissionStatus;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}





}
