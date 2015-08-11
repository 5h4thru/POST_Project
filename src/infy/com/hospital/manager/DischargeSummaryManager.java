package infy.com.hospital.manager;

import infy.com.hospital.service.DischargeSummaryService;
import infy.com.hospital.to.DischargeTO;

public class DischargeSummaryManager {
	public DischargeTO dischargeSummary(String registrationNo) throws Exception
	{
		try
		{
			DischargeTO dto=new DischargeSummaryService().dischargeSummary(registrationNo);
			return dto;
			
		}
		catch(Exception e)
		{
			throw e;
		}
	}

}
