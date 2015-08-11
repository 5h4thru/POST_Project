package infy.com.hospital.manager;

import infy.com.hospital.exception.CannotDelete;
import infy.com.hospital.exception.NoEmployees;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.EmployeeService;
import infy.com.hospital.to.EmployeeTO;

import java.util.List;

import javax.faces.model.SelectItem;

public class EmployeeManager {
	public String addReceptionist(EmployeeTO eto) throws Exception{
		try {
			EmployeeService service = new EmployeeService();
			return(service.addReceptionist(eto));
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addReceptionist", e.getMessage());
			throw e;
		}
	}

	public String addDoctor(EmployeeTO eto) throws Exception{
		try {
			
			if(eto.isS1())
				eto.setSlot1("AV");
			else
				eto.setSlot1("NA");
			if(eto.isS2())
				eto.setSlot2("AV");
			else
				eto.setSlot2("NA");
			if(eto.isS3())
				eto.setSlot3("AV");
			else
				eto.setSlot3("NA");
			
			EmployeeService service = new EmployeeService();
			return(service.addDoctor(eto));
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addDoctor", e.getMessage());
			throw e;
		}
	}

	public List<SelectItem> getNames() throws Exception,NoEmployees{
		try {
			EmployeeService service = new EmployeeService();
			List<SelectItem> list = service.getNames();
			if(list.size()==0){
				throw new NoEmployees();
			}
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getNames", e.getMessage());
			throw e;
		}
	}
	
	public List<SelectItem> getDocs() throws Exception, NoEmployees{
		try {
			EmployeeService service = new EmployeeService();
			List<SelectItem> list = service.getDocs();
			if(list.size()==0){
				throw new NoEmployees();
			}
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDocs", e.getMessage());
			throw e;
		}
	}
	

	public String delEmployee(String userName) throws Exception{
		try {
			EmployeeService service = new EmployeeService();
			return(service.delEmployee(userName));
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "delEmployee", e.getMessage());
			throw new CannotDelete();
		}
	}

	public List<EmployeeTO> getTO(String userName) throws Exception{
		try {
			EmployeeService service = new EmployeeService();
			return(service.getTO(userName));
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getTO", e.getMessage());
			throw e;
		}
	}


	public void updateDoctor(EmployeeTO eto) throws Exception{
		EmployeeService service = new EmployeeService();
		try {
			service.updateDoctor(eto);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateDoctor", e.getMessage());
			throw e;
		}
	}

}