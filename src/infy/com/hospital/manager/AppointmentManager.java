package infy.com.hospital.manager;

import infy.com.hospital.exception.InvalidUserNameException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.AppointmentService;
import infy.com.hospital.to.OpdAppointmentTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AppointmentManager {
public List<OpdAppointmentTO> viewAppointment(String userName,Date date) throws Exception
{
	 try{
		 List<OpdAppointmentTO> opList=new ArrayList<OpdAppointmentTO>();
		 opList=new AppointmentService().viewAppointment(userName,date);
		 if(opList.isEmpty()==true){
			 throw new InvalidUserNameException("Invalid userName");
		 }
		 return opList;
	 }
	 catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"viewAppointment", e.getMessage());
		throw e;
	}	
}
}
