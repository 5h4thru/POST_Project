package infy.com.hospital.managedbean;

import infy.com.hospital.exception.NoEmployees;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.ViewScheduleManager;
import infy.com.hospital.to.DoctorTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;


public class ViewScheduleBean {
	private String doctorName;
	private String specialization;
	private String message;
	private String select;
	private List<DoctorTO> dto = new ArrayList<DoctorTO>();
	private List<SelectItem> doctorNames = new ArrayList<SelectItem>();
	private List<SelectItem> specializationNames = new ArrayList<SelectItem>();

	public ViewScheduleBean(){
		ViewScheduleManager manager = new ViewScheduleManager();
		try {
			doctorNames = manager.getNames();
			specializationNames = manager.getSpecialization();
		} catch (NoEmployees e) {
			this.message = e.getMessage();
			ErrorLogger.logError(this.getClass().getName(), "ViewSchedule", e.getMessage());
		} catch (Exception e) {
			this.message = e.getMessage();
			ErrorLogger.logError(this.getClass().getName(), "ViewSchedule", e.getMessage());
		}
	}



	public String getDoctorSchedule(){
		ViewScheduleManager manager = new ViewScheduleManager();
		try {
			dto = manager.getDoctorSchedule(this.doctorName);
			for (int i = 0; i < dto.size(); i++) {
				if(dto.get(i).getSlot1().equals("AV"))
					dto.get(i).setSlot1("Available");
				else
					dto.get(i).setSlot1("Not Available");


				if(dto.get(i).getSlot2().equals("AV"))
					dto.get(i).setSlot2("Available");
				else
					dto.get(i).setSlot2("Not Available");


				if(dto.get(i).getSlot3().equals("AV"))
					dto.get(i).setSlot3("Available");
				else
					dto.get(i).setSlot3("Not Available");
			}
		} catch (Exception e1) {
			this.message = e1.getMessage();
			ErrorLogger.logError(this.getClass().getName(), "getDoctorSchedule", e1.getMessage());	
		}
		return "";
	}


	public String getSpecialSchedule(){
		ViewScheduleManager manager = new ViewScheduleManager();
		try {
			dto = manager.getSpecialSchedule(this.specialization);
			for (int i = 0; i < dto.size(); i++) {
				if(dto.get(i).getSlot1().equals("AV"))
					dto.get(i).setSlot1("Available");
				else
					dto.get(i).setSlot1("Not Available");


				if(dto.get(i).getSlot2().equals("AV"))
					dto.get(i).setSlot2("Available");
				else
					dto.get(i).setSlot2("Not Available");


				if(dto.get(i).getSlot3().equals("AV"))
					dto.get(i).setSlot3("Available");
				else
					dto.get(i).setSlot3("Not Available");
			}
		} catch (Exception e1) {
			this.message = e1.getMessage();
			ErrorLogger.logError(this.getClass().getName(), "getSpecialSchedule", e1.getMessage());			
		}
		return "";

	}


	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public List<SelectItem> getDoctorNames() {
		return doctorNames;
	}
	public void setDoctorNames(List<SelectItem> doctorNames) {
		this.doctorNames = doctorNames;
	}
	public List<SelectItem> getSpecializationNames() {
		return specializationNames;
	}
	public void setSpecializationNames(List<SelectItem> specializationNames) {
		this.specializationNames = specializationNames;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public List<DoctorTO> getDto() {
		return dto;
	}
	public void setDto(List<DoctorTO> dto) {
		this.dto = dto;
	}
	public String getSelect() {
//		System.out.println(select);
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
}