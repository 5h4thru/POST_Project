package infy.com.hospital.to;

import java.util.Date;

public class OpdAppointmentTO {
private String registrationNo;
private String patientId;
private String doctorId;
private Date dateOfAdmission;
private String reasonForAppointment;
private String slot;
private boolean selected;
private String status;
public String getRegistrationNo() {
	return registrationNo;
}
public void setRegistrationNo(String registrationNo) {
	this.registrationNo = registrationNo;
}
public String getPatientId() {
	return patientId;
}
public void setPatientId(String patientId) {
	this.patientId = patientId;
}
public String getDoctorId() {
	return doctorId;
}
public void setDoctorId(String doctorId) {
	this.doctorId = doctorId;
}
public Date getDateOfAdmission() {
	return dateOfAdmission;
}
public void setDateOfAdmission(Date dateOfAdmission) {
	this.dateOfAdmission = dateOfAdmission;
}
public String getReasonForAppointment() {
	return reasonForAppointment;
}
public void setReasonForAppointment(String reasonForAppointment) {
	this.reasonForAppointment = reasonForAppointment;
}
public String getSlot() {
	return slot;
}
public void setSlot(String slot) {
	this.slot = slot;
}
public boolean isSelected() {
	return selected;
}
public void setSelected(boolean selected) {
	this.selected = selected;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}

}
