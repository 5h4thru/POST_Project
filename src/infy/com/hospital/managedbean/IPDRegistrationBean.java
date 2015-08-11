package infy.com.hospital.managedbean;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.IPDRegistrationManager;
import infy.com.hospital.to.IpdAppointmentTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class IPDRegistrationBean {
IpdAppointmentTO appointmentTO;
private List<SelectItem> doctors;
private List<SelectItem> wards;
private List<SelectItem> beds;
private String department;
private String doctorId;
private String wardType;
private String message;
public IPDRegistrationBean()
{
	try{
		this.message=null;
	doctors=new ArrayList<SelectItem>();
	wards=new ArrayList<SelectItem>();
	appointmentTO=new IpdAppointmentTO();
	beds=new ArrayList<SelectItem>();
	doctors=new IPDRegistrationManager().getDoctors1();
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"IPDRegistrationBean", e.getMessage());
		this.message=e.getMessage();
	}
}
public IpdAppointmentTO getAppointmentTO() {
	return appointmentTO;
}
public void setAppointmentTO(IpdAppointmentTO appointmentTO) {
	this.appointmentTO = appointmentTO;
}
public List<SelectItem> getDoctors() {
	return doctors;
}
public void setDoctors(List<SelectItem> doctors) {
	this.doctors = doctors;
}
public List<SelectItem> getWards() {
	return wards;
}
public void setWards(List<SelectItem> wards) {
	this.wards = wards;
}
public List<SelectItem> getBeds() {
	return beds;
}
public void setBeds(List<SelectItem> beds) {
	this.beds = beds;
}
public String getDepartment() {
	return department;
}
public void setDepartment(String department) {
	this.department = department;
}
public String getDoctorId() {
	return doctorId;
}
public void setDoctorId(String doctorId) {
	this.doctorId = doctorId;
}
public String getWardType() {
	return wardType;
}
public void setWardType(String wardType) {
	this.wardType = wardType;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}

public void getDepartmentName(ValueChangeEvent e)
{
	try{
		this.message=null;
	String doctorId=e.getNewValue().toString();
	department=new IPDRegistrationManager().getDepartmentName(doctorId);
	}
	catch (Exception e1) {
		ErrorLogger.logError(this.getClass().getName(),"getDepartmentName", e1.getMessage());
	}
}
public void getWardsList(ValueChangeEvent e)
{
	try{
		this.message=null;
		String wardType=e.getNewValue().toString();
		
		wards=new IPDRegistrationManager().getWards(wardType);
	}
	catch (Exception e1) {
		ErrorLogger.logError(this.getClass().getName(),"getWards", e1.getMessage());
		this.message=e1.getMessage();
	}
}
public void getBedsList(ValueChangeEvent e)
{
	try{
		this.message=null;
	beds.clear();
		String wardNo=e.getNewValue().toString();
		List<Object> bList=new IPDRegistrationManager().getBeds(wardNo);
		for(Object s:bList)
		{
			beds.add(new SelectItem(s.toString(),"Bed"+s.toString()));
		}
	}
	catch (Exception e1) {
		ErrorLogger.logError(this.getClass().getName(),"getbedsList", e1.getMessage());
	this.message=e1.getMessage();
	}
}

public String addPatient()
{
	try{
		this.message=null;
		Date d=new Date();
	//appointmentTO=new IpdAppointmentTO();
	//appointmentTO.setDoctorId(this.doctorId);
	appointmentTO.setDepartment(this.department);
	appointmentTO.setDateOfAdmission(d);
	appointmentTO.setDateOfDischarge(null);
	appointmentTO.setAdmissionStatus("A");
	String regNo=new IPDRegistrationManager().ipdRegisterPatient(this.appointmentTO);
if(regNo!=null)
{
	this.message="Patient Registered Successfully with registration No."+regNo;
	return "success";
}
else
{
	return "failure";
}
	}
	catch (Exception e1) {
		ErrorLogger.logError(this.getClass().getName(),"addPatient", e1.getMessage());
		this.message=e1.getMessage();
		return "failure";
	}
}

}
