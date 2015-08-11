package infy.com.hospital.manager;

import infy.com.hospital.exception.InvalidPatientIdException;
import infy.com.hospital.exception.InvalidScheduleException;
import infy.com.hospital.exception.InvalidScheduleForTodayException;
import infy.com.hospital.exception.NoDoctorFoundException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.DoctorService;
import infy.com.hospital.to.DoctorTO;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.EmployeeTO;
import infy.com.hospital.to.OTTO;
import infy.com.hospital.to.SurgeryScheduleTO;

import java.util.Date;
import java.util.List;

public class DoctorManager {
	/*public List<DoctorTO> getDoctor(String department){
		
	}*/
	public void requestMedicine(DrugRequestTO drugRequestTO) throws Exception
	{
		try{
		new DoctorService().requestMedicine(drugRequestTO);
		}
		catch (Exception e) {
			ErrorLogger.logError(e.getClass().getName(), "requestMedicineManager", e.getMessage());
			throw e;
		}
	}
	
	public List<DoctorTO> getDoctor(String docId, String department, String slot) throws NoDoctorFoundException, Exception {
		try {
			DoctorService dservice = new DoctorService();
			List<DoctorTO> doctorList = dservice.getDoctor(docId, department, slot);
			
			if(doctorList.size() == 0 || doctorList == null) {
				throw new NoDoctorFoundException();
			}
			
			return doctorList;
		}
		
		catch(NoDoctorFoundException e) {
			ErrorLogger.logError(this.getClass().getName(), "getDoctor", e.getMessage());
			throw e;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDoctor", e.getMessage());
			throw e;
		}
	}
	
	
	public List<DoctorTO> getDepartmentList() throws Exception {
		try {
			DoctorService dservice = new DoctorService();
			List<DoctorTO> doctorList = dservice.getDepartmentList();
			return doctorList;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDepartmentList", e.getMessage());
			throw e;
		}
	}
	

	
	
	
	public Boolean updateDoctorDetails(DoctorTO doctorto,EmployeeTO eto) throws Exception{
		try
		{
			DoctorService ds=new DoctorService();
			Boolean b=ds.updateDoctorDetails(doctorto, eto);
			return b;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"updateDoctorDetails",e.getMessage());
			throw e;
		}
		
	}
	public String scheduleSurgery(SurgeryScheduleTO sst) throws InvalidScheduleForTodayException,InvalidScheduleException ,Exception
	{
		try
		{
			Date today = new Date();
			Date surgeryDate = sst.getDateOfSurgery();
			
			if(surgeryDate.getDate()==today.getDate() && surgeryDate.getMonth()==today.getMonth() && surgeryDate.getYear()==today.getYear())
			{
				String slot = sst.getSlot();
				if(slot.equals("S1") && today.getHours()>=9)
				{
					throw new InvalidScheduleForTodayException();
				}
				if(slot.equals("S2") && today.getHours()>=12)
				{
					throw new InvalidScheduleForTodayException();
				}
				if(slot.equals("S3") && today.getHours()>=15)
				{
					throw new InvalidScheduleForTodayException();
				}
			}
		DoctorService ds=new DoctorService();
		String bId=ds.scheduleSurgery(sst);
		if(bId!=null)
		{
			return bId;
		}
		else
		{
			throw new InvalidScheduleException();
		}
		}
		catch(InvalidScheduleForTodayException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"scheduleSurgery",e.getMessage());
			throw e;
		}
		catch(InvalidScheduleException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"scheduleSurgery",e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"scheduleSurgery",e.getMessage());
			throw e;
		}
	}
	public DoctorTO getDoctorDetails(String userName) throws Exception{
		try{
			DoctorService ds=new DoctorService();
			DoctorTO dto=ds.getDoctorDetails(userName);
			return dto;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getDoctorDetails",e.getMessage());
			throw e;
		}
	}
	public EmployeeTO getEmployeeDetails(String userName) throws Exception{
		try{	
			DoctorService ds=new DoctorService();
			EmployeeTO eto=ds.getEmployeeDetails(userName);
			return eto;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"getEmployeeDetails",e.getMessage());
			throw e;
		}
	}
	public List<OTTO> listOfOT() throws Exception{
		try{	
			DoctorService ds=new DoctorService();
			List<OTTO> list=ds.listOfOT();
			return list;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"listOfOT",e.getMessage());
			throw e;
		}
	}
	public Boolean validatePatientId(String patientId) throws InvalidPatientIdException,Exception
	{
		try
		{
		DoctorService ds=new DoctorService();
		if(ds.validatePatientId(patientId))
		{
			return true;
		}
		else
		{
			throw new InvalidPatientIdException();
		}
		}
		catch(InvalidPatientIdException e)
		{
			ErrorLogger.logError(this.getClass().getName(),"validatePatientId",e.getMessage());
			throw e;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"validatePatientId",e.getMessage());
			throw e;
		}
		
	}
	/*public List<DoctorTO> getDepartmentList(){
		
	}*/
}
