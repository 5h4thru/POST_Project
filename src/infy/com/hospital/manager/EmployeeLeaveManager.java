package infy.com.hospital.manager;

import infy.com.hospital.exception.LeaveException;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.EmployeeLeaveService;
import infy.com.hospital.to.EmployeeLeaveTO;

import java.util.Date;


public class EmployeeLeaveManager {


	public String leaveApplication(EmployeeLeaveTO eto) throws Exception{
		String leaveId = null;
		EmployeeLeaveService service = new EmployeeLeaveService();
		try {
			leaveId = service.leaveApplication(eto);
			return leaveId;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "leaveApplication", e.getMessage());
			throw e;
		}
	}


	public boolean checkSchedule(Date toDate, Date fromDate, String userName) throws Exception, LeaveException{
		EmployeeLeaveService service = new EmployeeLeaveService();
		try {
			boolean check = service.checkSchedule(toDate, fromDate, userName);
			if (check){
				return true;
			}
			else{
				throw new LeaveException();
			}
		} catch(LeaveException e){ 
			ErrorLogger.logError(this.getClass().getName(), "checkSchedule", e.getMessage());
			throw e;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "checkSchedule", e.getMessage());
			throw e;
		}
	}
}