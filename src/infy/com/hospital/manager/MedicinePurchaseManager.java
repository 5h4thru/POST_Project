package infy.com.hospital.manager;

import infy.com.hospital.exception.InsufficientQuantityException;
import infy.com.hospital.exception.InvalidPatientDetailsException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.MedicinePurchaseService;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.DrugTO;

import java.util.List;

public class MedicinePurchaseManager 
{

	public List<DrugTO> listOfDrugs() throws Exception
	{
		try
		{
		MedicinePurchaseService mps=new MedicinePurchaseService();
		return mps.listOfDrugs();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"listOfDrugs",e.getMessage());
			throw e;
		}	
	}
	
	public String makePayment(DrugRequestTO drt) throws InsufficientQuantityException,Exception
	{
		try
		{
		MedicinePurchaseService mps=new MedicinePurchaseService();
		if(mps.makePayment(drt)==null)
		{
			throw new InsufficientQuantityException();
		}
		return mps.makePayment(drt);
		
		}
		catch(InsufficientQuantityException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"makePayment",e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"makePayment",e.getMessage());
			throw e;
		}
	}
	
//	public int medicinePrice(String medicineName) throws Exception
//	{
//		MedicinePurchaseService mds=new MedicinePurchaseService();
//		try 
//		{
//			return mds.medicinePrice(medicineName);
//		} 
//		catch (Exception e) 
//		{
//			ErrorLogger.logError(this.getClass().getName(),"medicinePrice",e.getMessage());
//			throw e;
//		}
//		
//	}
	
	
	
//	public boolean checkMedicineAvailability(DrugRequestTO drt)
//	{
//		return true;
//	}

	public Boolean validatePatientDetails(String patientType,
			String registrationNo) throws InvalidPatientDetailsException, Exception
	{
		try
		{
		MedicinePurchaseService mps=new MedicinePurchaseService();
		if(mps.validatePatientDetails(patientType,registrationNo))
		{
			return true;
		}
		else
		{
			throw new InvalidPatientDetailsException();
		}
		}
		catch(InvalidPatientDetailsException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"validatePatientDetails",e.getMessage());
			throw e;
		}
		catch(Exception e)
		{ 
			ErrorLogger.logError(this.getClass().getName(),"validatePatientDetails",e.getMessage());
			throw e;
		}
	}
}
