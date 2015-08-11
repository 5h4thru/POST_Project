package infy.com.hospital.manager;

import infy.com.hospital.exception.AmountRequiredException;
import infy.com.hospital.exception.BillListEmptyException;
import infy.com.hospital.exception.CannotBeDischargedException;
import infy.com.hospital.exception.NoRegistrationNoFoundException;
import infy.com.hospital.exception.PatientDischargedAlreadyException;
import infy.com.hospital.exception.PatientIDPatternException;
import infy.com.hospital.exception.PatientIdNotPresentException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.BillingService;
import infy.com.hospital.to.BillingTO;

import java.util.List;

public class BillingManager {
	public BillingTO payIPD(String registrationNo,String patientId,Double amount) throws Exception{
		BillingTO billingTO=null;
			try{
			int var=new BillingService().validations(registrationNo, patientId);
			if(var==-1){
				throw new NoRegistrationNoFoundException();
			}
			if(var==-3){
				if(!(patientId.substring(0, 2).equals("PA"))){
					throw new PatientIDPatternException();
				}
				else
				throw new PatientIdNotPresentException();
			}
			if(var==-2){
				throw new PatientDischargedAlreadyException();
			}
			if(!(patientId.substring(0, 2).equals("PA"))){
				throw new PatientIDPatternException();
			}
			if(amount==null){
				throw new AmountRequiredException();
			}
			billingTO=new BillingService().payIPD(registrationNo, patientId, amount);
			}
			catch (NoRegistrationNoFoundException e) {
				ErrorLogger.logError(e.getClass().getName(), "payIPD",e.getMessage());
				throw e;
			}
			catch (AmountRequiredException e) {
				ErrorLogger.logError(e.getClass().getName(), "payIPD",e.getMessage());
				throw e;
			}
			catch (PatientIdNotPresentException e) {
				ErrorLogger.logError(e.getClass().getName(), "payIPD",e.getMessage());
				throw e;
			}
			catch (PatientIDPatternException e) {
				ErrorLogger.logError(e.getClass().getName(), "payIPD",e.getMessage());
				throw e;
			}
			catch (Exception e) {
				ErrorLogger.logError(e.getClass().getName(), "payIPD",e.getMessage());
				throw e;
			}
			return billingTO;
	}
	
	public Double discharge(String registrationNo,String patientId) throws Exception{
		Double amount=null;
		try{
			int var=new BillingService().validations(registrationNo, patientId);
			if(var==-1){
				throw new NoRegistrationNoFoundException();
			}
			if(var==-3){
				if(!(patientId.substring(0, 2).equals("PA"))){
					throw new PatientIDPatternException();
				}
				else
				throw new PatientIdNotPresentException();
			}
			if(!(patientId.substring(0, 2).equals("PA"))){
				throw new PatientIDPatternException();
			}
			if(var==-2){
				throw new PatientDischargedAlreadyException();
			}
			amount=new BillingService().discharge(registrationNo, patientId);
		
		}
		catch (NoRegistrationNoFoundException e) {
			ErrorLogger.logError(e.getClass().getName(), "discharge",e.getMessage());
			throw e;
		}
		catch (CannotBeDischargedException e) {
			ErrorLogger.logError(e.getClass().getName(), "discharge",e.getMessage());
			throw e;
		}
		catch (PatientDischargedAlreadyException e) {
			ErrorLogger.logError(e.getClass().getName(), "discharge",e.getMessage());
			throw e;
		}
		catch (PatientIdNotPresentException e) {
			ErrorLogger.logError(e.getClass().getName(), "discharge",e.getMessage());
			throw e;
		}
		catch (PatientIDPatternException e) {
			ErrorLogger.logError(e.getClass().getName(), "discharge",e.getMessage());
			throw e;
		}
		catch (Exception e) {
			ErrorLogger.logError(e.getClass().getName(), "discharge",e.getMessage());
			throw e;
		}
		return amount;
	}
	public List<BillingTO> generateBill(String registrationNo,String patientId) throws Exception{
		List<BillingTO> billList=null;
		try{
			int var=new BillingService().validations(registrationNo, patientId);
			if(var==-1){
				throw new NoRegistrationNoFoundException();
			}
			if(var==-3){
				if(!(patientId.substring(0, 2).equals("PA"))){
					throw new PatientIDPatternException();
				}
				else
				throw new PatientIdNotPresentException();
			}
			if(!(patientId.substring(0, 2).equals("PA"))){
				throw new PatientIDPatternException();
			}
			
			
			billList=new BillingService().generateBill(registrationNo, patientId);
			if(billList.isEmpty()){
				throw new BillListEmptyException();
			}
			}
			catch (PatientDischargedAlreadyException e) {
			ErrorLogger.logError(e.getClass().getName(), "generateBill",e.getMessage());
			throw e;
			}
			catch (PatientIdNotPresentException e) {
				ErrorLogger.logError(e.getClass().getName(), "generateBill",e.getMessage());
				throw e;
			}
			catch (PatientIDPatternException e) {
				ErrorLogger.logError(e.getClass().getName(), "generateBill",e.getMessage());
				throw e;
			}
			catch (NoRegistrationNoFoundException e) {
				ErrorLogger.logError(e.getClass().getName(), "generateBill",e.getMessage());
				throw e;
			}
			catch (Exception e) {
				ErrorLogger.logError(e.getClass().getName(), "generateBill",e.getMessage());
				throw e;
			}
			
		return billList;
	}
	
}
