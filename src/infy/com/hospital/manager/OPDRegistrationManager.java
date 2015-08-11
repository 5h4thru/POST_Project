package infy.com.hospital.manager;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.OPDRegistrationService;
import infy.com.hospital.to.BillingTO;
import infy.com.hospital.to.OpdAppointmentTO;
import infy.com.hospital.to.PatientTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class OPDRegistrationManager {
public String addPatient(PatientTO patientTO){
	try{
	String patientId=new OPDRegistrationService().addPatient(patientTO);
	if(patientId!=null)
	{
		return patientId;
	}
	else
	{
		return null;
	}
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"addPatient",e.getMessage());
		return null;
	}
}
public void updateUserName(String patientId) throws Exception
{
	try {
		new OPDRegistrationService().updateUserName(patientId);
	} 
	catch (Exception e) {
		// TODO Auto-generated catch block
		ErrorLogger.logError(this.getClass().getName(),"updateUserName", e.getMessage());
		throw e;
	}
}
public List<SelectItem> getDoctors1() throws Exception
{
	try
	{
	OPDRegistrationService s=new OPDRegistrationService();
	List<SelectItem> li=new ArrayList<SelectItem>();
	li=s.getDoctors1();
	
	if(li.size()!=0)
	{
	return li;
	}
	else
	{
		return null;
	}
	}
	catch(Exception e)
	{
		ErrorLogger.logError(this.getClass().getName(),"getDoctors1",e.getMessage());
		throw e;
	}
}
public List<SelectItem> getSlots(String doctorName) throws Exception
{
	try
	{
	OPDRegistrationService s1=new OPDRegistrationService();
	List<SelectItem> li1=new ArrayList<SelectItem>();
	li1=s1.getSlots(doctorName);
	
	if(li1.size()!=0)
	{
	return li1;
	}
	else
	{
		return null;
	}
	}
	catch(Exception e)
	{
		ErrorLogger.logError(this.getClass().getName(),"getSlots",e.getMessage());
		throw e;
	}
}
public String registerOPD(OpdAppointmentTO opdAppointmentTO) throws Exception
{
	try
	{
	OPDRegistrationService registrationService=new OPDRegistrationService();
	String regNo=registrationService.registerOPD(opdAppointmentTO);
	return regNo;
	}
	
	catch(Exception e)
	{
		ErrorLogger.logError(this.getClass().getName(),"registerOPD",e.getMessage());
		throw e;
	}
}
public String addBillDetails(BillingTO billingTO) throws Exception
{
	try
	{
	OPDRegistrationService registrationService=new OPDRegistrationService();
	String billNo=registrationService.addBillDetails(billingTO);
	return billNo;
	}
	catch(Exception e)
	{
		ErrorLogger.logError(this.getClass().getName(),"addBillDetails",e.getMessage());
		throw e;
	}
}
public boolean checkPatientId(String userName) throws Exception
{
	try
	{
		OPDRegistrationService registrationService=new OPDRegistrationService();
		return registrationService.checkPatientId(userName);
	}
	catch(Exception e)
	{
		ErrorLogger.logError(this.getClass().getName(),"checkPatientId", e.getMessage());
		throw e;
	}
}
}
