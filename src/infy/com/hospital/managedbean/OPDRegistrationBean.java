package infy.com.hospital.managedbean;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.OPDRegistrationManager;
import infy.com.hospital.to.BillingTO;
import infy.com.hospital.to.OpdAppointmentTO;
import infy.com.hospital.to.PatientTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;


public class OPDRegistrationBean {
private PatientTO patientTO;
private String patientUserName;
private String message;
private String regNO;
private String billNo;
private BillingTO billingTO=new BillingTO();
private List<BillingTO> billList=new ArrayList<BillingTO>();
private OpdAppointmentTO opdAppointmentTO=new OpdAppointmentTO();
private List<SelectItem> doctors=new ArrayList<SelectItem>();
private List<SelectItem> slots=new ArrayList<SelectItem>();
private String docUserName;
private Integer patientType;

public Integer getPatientType() 
{
	return patientType;
}
public void setPatientType(Integer patientType) {
	this.patientType = patientType;
}
public PatientTO getPatientTO() {
 return patientTO;
}
public void setPatientTO(PatientTO patientTO) {
	this.patientTO = patientTO;
}
public String getPatientUserName() {
	return patientUserName;
}
public void setPatientUserName(String patientUserName) {
	this.patientUserName = patientUserName;
}
public String getMessage() {
	return message;
}
public void setMessage(String message) {
	this.message = message;
}
public String getRegNO() {
	return regNO;
}
public void setRegNO(String regNO) {
	this.regNO = regNO;
}
public String getBillNo() {
	return billNo;
}
public void setBillNo(String billNo) {
	this.billNo = billNo;
}
public BillingTO getBillingTO() {
	return billingTO;
}
public void setBillingTO(BillingTO billingTO) {
	this.billingTO = billingTO;
}
public List<BillingTO> getBillList() {
	return billList;
}
public void setBillList(List<BillingTO> billList) {
	this.billList = billList;
}
public OpdAppointmentTO getOpdAppointmentTO() {
	return opdAppointmentTO;
}
public void setOpdAppointmentTO(OpdAppointmentTO opdAppointmentTO) {
	this.opdAppointmentTO = opdAppointmentTO;
}
public List<SelectItem> getDoctors() {
	return doctors;
}
public void setDoctors(List<SelectItem> doctors) {
	this.doctors = doctors;
}
public List<SelectItem> getSlots() {
	return slots;
}
public void setSlots(List<SelectItem> slots) {
	this.slots = slots;
}
public String getDocUserName() {
	return docUserName;
}
public void setDocUserName(String docUserName) {
	this.docUserName = docUserName;
}
public OPDRegistrationBean()
{
	try{
		this.message=null;
		patientTO=new PatientTO();
		//opdAppointmentTO=new OpdAppointmentTO();
	OPDRegistrationManager opd=new OPDRegistrationManager();
	//doctors=new ArrayList<SelectItem>();
	doctors=opd.getDoctors1();
	}
	catch (Exception e) {
		//e.printStackTrace();
		ErrorLogger.logError(this.getClass().getName(),"OPDRegistrationBean",e.getMessage());
		this.message=e.getMessage();
	}
	}
public String addPatient()
{
try{
	this.message=null;
 String patientId=new OPDRegistrationManager().addPatient(patientTO);

 if(patientId!=null){
	
	 patientUserName="PA"+patientId;
	 opdAppointmentTO.setPatientId(patientUserName);
	 this.message="patient"+patientUserName+"added successfully";
 new OPDRegistrationManager().updateUserName(patientId);
 return "succes";
 }
 else
 {
	 this.message="sorry patient not added";
	 return "failure";
 }
}
catch (Exception e) {
	ErrorLogger.logError(this.getClass().getName(),"addPatient",e.getMessage());
	this.message=e.getMessage();
	return "failure";	
}
}



public String scheduleAndGenerateBill()
{
	try
	{
		this.message=null;
		OPDRegistrationManager registrationManager=new OPDRegistrationManager();
		if(patientType==1)
		{
			this.regNO=registrationManager.registerOPD(opdAppointmentTO);
			
			if(regNO==null)
			{	
				this.message="The slot is already booked with 3 patients.please try some other slot";
				return "failure";
			}
			else if(regNO.equals("registered"))
			{
				this.message="The Appointment is already fixed for this patient on this slot";
				return "failure";
			}
			else
			{
				billingTO.setRegistrationNo(regNO);
				Date d=new Date();
				billingTO.setBillingDate(d);
				this.billNo=registrationManager.addBillDetails(billingTO);
				this.message="Cash Accepted Successfully";
				return "success";
			}
		}
		else
		{
		if(registrationManager.checkPatientId(opdAppointmentTO.getPatientId()))
		{
		this.regNO=registrationManager.registerOPD(opdAppointmentTO);
		
		if(regNO==null)
		{	
			this.message="The slot is already booked with 3 patients.please try some other slot";
			return "failure";
		}
		else if(regNO.equals("registered"))
		{
			this.message="The Appointment is already fixed for this patient on this slot";
			return "failure";
		}
		else
		{
			billingTO.setRegistrationNo(regNO);
			Date d=new Date();
			billingTO.setBillingDate(d);
			this.billNo=registrationManager.addBillDetails(billingTO);
			this.message="Cash Accepted Successfully";
			return "success";
		}
		}
		else
		{
			this.message="No records found";
			return "failure";
		}
		}
	}
	catch(Exception e)
	{
		ErrorLogger.logError(this.getClass().getName(),"scheduleAndGenerateBill",e.getMessage());
		this.message=e.getMessage();
		return "failure";
	}

}


public void getAvailableSlots(ValueChangeEvent e)
{
	try{
	this.message=null;
	docUserName=(String) e.getNewValue();
	slots=new OPDRegistrationManager().getSlots(docUserName);
	}
	catch (Exception e1) {
		ErrorLogger.logError(this.getClass().getName(),"getAvailableSlots",e1.getMessage());
		this.message=e1.getMessage();	
	}	
}
public String kindOfPatient()
{
	FacesContext context=FacesContext.getCurrentInstance();
	Map<String,String> params=context.getExternalContext().getRequestParameterMap();
	this.patientType=Integer.parseInt(params.get("patient"));
	//System.out.println(this.patientType);
//	if(this.patientType.equalsIgnoreCase("new"))
//	{
//		this.patientTO.setSelected(true);
//	}
//	else if(this.patientType.equalsIgnoreCase("existing"))
//	{
//		this.patientTO.setSelected(false);
//	}
	return "succes";
}
}
