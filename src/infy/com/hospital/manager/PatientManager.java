package infy.com.hospital.manager;

import infy.com.hospital.exception.BusyScheduleException;
import infy.com.hospital.exception.InvalidDoctorIdException;
import infy.com.hospital.exception.MoreNoOfAppointmentsException;
import infy.com.hospital.exception.NoAppointmentException;
import infy.com.hospital.exception.NoPaymentMadeException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.PatientService;
import infy.com.hospital.to.DoctorTO;
import infy.com.hospital.to.OpdAppointmentTO;
import infy.com.hospital.to.PatientTO;
import infy.com.hospital.to.PaymentTO;

import java.util.List;

public class PatientManager {
	public DoctorTO checkAppointment(OpdAppointmentTO oTO) throws InvalidDoctorIdException, MoreNoOfAppointmentsException, BusyScheduleException, Exception {
		try {
			PatientService pservice = new PatientService();
			DoctorTO dTO = pservice.checkAppointment(oTO);
			int flag = 0;
			
			if(dTO == null) {
				throw new InvalidDoctorIdException();
			}
			
			int count = pservice.noOfAppointments(oTO);
			if(count >= 2) {
				throw new MoreNoOfAppointmentsException();
			}
			
			if(oTO.getSlot().equals("S1")) {
				if(dTO.getSlot1().equalsIgnoreCase("NA")) {
					flag = 1;
				}
			}
			if(oTO.getSlot().equals("S2")) {
				if(dTO.getSlot2().equalsIgnoreCase("NA")) {
					flag = 1;
				}
			}
			if(oTO.equals("S3")) {
				if(dTO.getSlot3().equalsIgnoreCase("NA")) {
					flag = 1;
				}
			}
			
			if(flag == 1) {
				throw new BusyScheduleException();
			}
			
			return dTO;
		}
		
		catch(InvalidDoctorIdException e) {
			ErrorLogger.logError(this.getClass().getName(), "checkAppointment", e.getMessage());
			throw e;
		}
		
		catch(MoreNoOfAppointmentsException e) {
			ErrorLogger.logError(this.getClass().getName(), "checkAppointment", e.getMessage());
			throw e;
		}
		
		catch(BusyScheduleException e) {
			ErrorLogger.logError(this.getClass().getName(), "checkAppointment", e.getMessage());
			throw e;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "checkAppointment", e.getMessage());
			throw e;
		}
	}
	
	public String makePayment(PaymentTO pt, OpdAppointmentTO oTO) throws Exception {
		try {
			PatientService pservice = new PatientService();
			String payNo = pservice.makePayment(pt, oTO);
			return payNo;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "makePayment", e.getMessage());
			throw e;
		}
	}
	
	public List<OpdAppointmentTO> viewAppointmentMade(String patientId) throws NoAppointmentException, Exception {
		try {
			PatientService pservice = new PatientService();
			List<OpdAppointmentTO> opdTO = pservice.viewAppointmentMade(patientId);
			
			if(opdTO.size() == 0 || opdTO == null) {
				throw new NoAppointmentException();
			}
			
			else {
				return opdTO;
			}
		}
		
		catch(NoAppointmentException e) {
			ErrorLogger.logError(this.getClass().getName(), "viewAppointmentMade", e.getMessage());
			throw e;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "viewAppointmentMade", e.getMessage());
			throw e;
		}
	}
	
	public void cancelAppointment(String regNo) throws Exception {
		try {
			PatientService pservice = new PatientService();
			pservice.cancelAppointment(regNo);
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "cancelAppointment", e.getMessage());
			throw e;
		}
	}
	public List<PaymentTO> viewPayments(String patientId)throws NoPaymentMadeException,Exception
	{
		try
		{	
		PatientService patientService=new PatientService();
		List<PaymentTO> list=patientService.viewPayments(patientId);
		if(list.size()==0||list.isEmpty())
		{
			throw new NoPaymentMadeException();
		}
		
		return list;
		}
		catch(NoPaymentMadeException e)
		{
			ErrorLogger.logError(this.getClass().getName(), "viewPayments", e.getMessage());
			throw e;
		}
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "viewPayments", e.getMessage());
			throw e;
		}
		
	}
	
	public PatientTO findPatient(String patientId)throws Exception
	{
		try
		{
			PatientService patientService=new PatientService();
			
			//System.out.println(patientService.findPatient(patientId).getFirstName());
			return patientService.findPatient(patientId);
		}
		catch (Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "findPatient", e.getMessage());
			throw e;
		}
		
	}
	
}
