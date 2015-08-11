package infy.com.hospital.manager;

import infy.com.hospital.exception.InvalidPatientIdException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.InPatientEnquiryService;
import infy.com.hospital.to.IpdAppointmentTO;

public class InPatientEnquiryManager {

	public IpdAppointmentTO inpatientEnquiry(String patientUsername) throws InvalidPatientIdException,Exception
	{
		try {			
			InPatientEnquiryService ips=new InPatientEnquiryService();
			IpdAppointmentTO iat=new IpdAppointmentTO();
			iat=ips.inpatientEnquiry(patientUsername);
			
			if(iat==null)
			{
				throw new InvalidPatientIdException();
			}
			return iat;
		} 
		
		catch(InvalidPatientIdException ipe)
		{
			ErrorLogger.logError(this.getClass().getName(),"InPatientEnquiry",ipe.getMessage());
			throw ipe;
		}
		catch (Exception e1)
		{
			ErrorLogger.logError(this.getClass().getName(),"InPatientEnquiry",e1.getMessage());
			throw e1;
		}

	}


}
