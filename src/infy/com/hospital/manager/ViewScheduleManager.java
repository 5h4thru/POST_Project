package infy.com.hospital.manager;

import infy.com.hospital.exception.NoEmployees;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.ViewScheduleService;
import infy.com.hospital.to.DoctorTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;




public class ViewScheduleManager {
	public List<SelectItem> getNames() throws Exception, NoEmployees{
		List<SelectItem> list = new ArrayList<SelectItem>();
		ViewScheduleService service = new ViewScheduleService();
		try {
			list = service.getNames();
			if(list.size()==0)
				throw new NoEmployees();
		} catch(NoEmployees e){
			ErrorLogger.logError(this.getClass().getName(), "getNames", e.getMessage());
			throw e;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getNames", e.getMessage());
			throw e;
		}
		return list;
	}
	
	public List<SelectItem> getSpecialization() throws Exception, NoEmployees{
		List<SelectItem> list = new ArrayList<SelectItem>();
		ViewScheduleService service = new ViewScheduleService();
		try {
			list = service.getSpecialization();
			if(list.size()==0)
				throw new NoEmployees();
		} catch(NoEmployees e){
			ErrorLogger.logError(this.getClass().getName(), "getSpecialization", e.getMessage());
			throw e;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getSpecialization", e.getMessage());
			throw e;
		}
		return list;
	}
	
	public List<DoctorTO> getDoctorSchedule(String name) throws Exception{
		List<DoctorTO> list = new ArrayList<DoctorTO>();
		try {
			ViewScheduleService service = new ViewScheduleService();
			list = service.getDoctorSchedule(name);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDoctorSchedule", e.getMessage());
			throw e;
		}
		return list;
	}
	
	
	public List<DoctorTO> getSpecialSchedule(String name) throws Exception{
		List<DoctorTO> list = new ArrayList<DoctorTO>();
		try {
			ViewScheduleService service = new ViewScheduleService();
			list = service.getSpecialSchedule(name);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getSpecialSchedule", e.getMessage());
			throw e;
		}
		return list;
	}
}