package infy.com.hospital.manager;

import infy.com.hospital.exception.InvalidDoctorUserNameException;
import infy.com.hospital.exception.InvalidWardNoException;
import infy.com.hospital.exception.InvalidWardTypeException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.IPDRegistrationService;
import infy.com.hospital.to.IpdAppointmentTO;

import java.util.List;

import javax.faces.model.SelectItem;





public class IPDRegistrationManager {
public List<SelectItem> getDoctors1() throws Exception
{
	try {
		List<SelectItem> doctors=new IPDRegistrationService().getDoctors1();
		
			return doctors;
		
	} catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"getDoctors1", e.getMessage());
		throw e;
	}
	
}
public String getDepartmentName(String doctorId) throws Exception,InvalidDoctorUserNameException
{
	try{
		String department=new IPDRegistrationService().getDepartmentName(doctorId);
		if(department==null)
		{
			throw new InvalidDoctorUserNameException("No such doctor exists");
		}
		return department;
	}
	catch (InvalidDoctorUserNameException e) {
		ErrorLogger.logError(this.getClass().getName(),"getDepartment", e.getMessage());
		throw e;
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"getDepartment", e.getMessage());
		throw e;
	}
}
public List<SelectItem> getWards(String wardType) throws InvalidWardTypeException,Exception
{
	try{
	List<SelectItem> 	wards = new IPDRegistrationService().getWards(wardType);
	if(wards.isEmpty()==true)
	{
		throw new InvalidWardTypeException("No such ward type exists");
	}
		return wards;
	}
	catch (InvalidWardTypeException e) {
		ErrorLogger.logError(this.getClass().getName(),"getWards", e.getMessage());
		throw e;
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"getWards", e.getMessage());
		throw e;
	}
	
}
public List<Object> getBeds(String wardNo) throws InvalidWardNoException,Exception
{
	try{
List<Object> beds=new IPDRegistrationService().getBeds(wardNo);
if(beds.isEmpty()==true)
{
	throw new InvalidWardNoException("No such ward no exists");
}
return beds;
	}
	catch (InvalidWardNoException e) {
		ErrorLogger.logError(this.getClass().getName(),"getBeds", e.getMessage());
		throw e;
	}
	catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"getBeds", e.getMessage());
		throw e;
	}
}
public String ipdRegisterPatient(IpdAppointmentTO ipdAppointmentTO) throws Exception
{
	try {
		String regNo=new IPDRegistrationService().ipdRegisterPatient(ipdAppointmentTO);
		return regNo;
	} catch (Exception e) {
		ErrorLogger.logError(this.getClass().getName(),"ipdRegisterPatient", e.getMessage());
		throw e;
	}	
}
}
