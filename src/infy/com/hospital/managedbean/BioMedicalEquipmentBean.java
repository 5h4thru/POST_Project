package infy.com.hospital.managedbean;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.BioMedicalManager;
import infy.com.hospital.to.BiomedicalEquipmentTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
;

public class BioMedicalEquipmentBean {
	private BiomedicalEquipmentTO equipmentTO;
	private List<BiomedicalEquipmentTO> equipmentTO1;
	private static String message;
	private String errorMessage;
	private List<SelectItem> idList;
	private String equipmentNo;
	private String status;
	
	public void mess()
	{
		message="";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public BiomedicalEquipmentTO getEquipmentTO() {
		return equipmentTO;
	}
	public void setEquipmentTO(BiomedicalEquipmentTO equipmentTO) {
		this.equipmentTO = equipmentTO;
	}
	public List<BiomedicalEquipmentTO> getEquipmentTO1() {
		return equipmentTO1;
	}
	public void setEquipmentTO1(List<BiomedicalEquipmentTO> equipmentTO1) {
		this.equipmentTO1 = equipmentTO1;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message1) {
		message = message1;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public List<SelectItem> getIdList() {
		return idList;
	}
	public void setIdList(List<SelectItem> idList) {
		this.idList = idList;
	}
	public String getEquipmentNo() {
		return equipmentNo;
	}
	public void setEquipmentNo(String equipmentNo) {
		this.equipmentNo = equipmentNo;
	}
	public String navigate()
	{
		
		if(equipmentTO.getEquipmentName()!=null)
		{
			equipmentTO.setEquipmentName(null);
			equipmentTO.setCost(null);
			
			equipmentTO.setEquipmentNo(null);
			equipmentTO.setQuantity(null);
			equipmentTO.setDateOfPurchase(null);
		}
		this.status ="add";
		message="";
		this.equipmentNo="0";
		
		return null;
		
	}
	public String navigate1()
	{
		
		this.status ="del";
		message="";
		this.equipmentNo="0";
		if(equipmentTO.getEquipmentName()!=null)
		{
			equipmentTO.setEquipmentName(null);
			equipmentTO.setCost(null);
			equipmentTO.setQuantity(null);
			equipmentTO.setEquipmentNo(null);
		
			equipmentTO.setDateOfPurchase(null);
		}
		return null;
	}
	public String navigate2()
	{
		
		this.status ="modify";
		message="";
		this.equipmentTO1=null;
		this.equipmentNo="0";
		if(equipmentTO.getEquipmentName()!=null)
		{
			equipmentTO.setEquipmentName(null);
			equipmentTO.setCost(null);
			equipmentTO.setQuantity(null);
			equipmentTO.setEquipmentNo(null);
			equipmentTO.setDateOfPurchase(null);
			
		}
		return null;
	}
	public BioMedicalEquipmentBean()
	{
		this.equipmentTO1=null;
		this.status="";
		this.equipmentTO1=new ArrayList<BiomedicalEquipmentTO>();
		this.idList=new ArrayList<SelectItem>();
		equipmentTO=new BiomedicalEquipmentTO();
		try
		{
		BioMedicalManager bioMedicalManager=new BioMedicalManager();
		this.idList=bioMedicalManager.getIds();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "biomedicalEquipmentBean", e.getMessage());
			message=e.getMessage();
		}
	}
	
	public String addEquipment()
	{
		try
		{
		//this.message="";
		BioMedicalManager bioMedicalManager=new BioMedicalManager();
		this.equipmentNo=bioMedicalManager.addEquipment(equipmentTO);
		this.idList=bioMedicalManager.getIds();
		message="Equipment "+equipmentNo+" added successfully";
		return "success";
		}
		
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "addEquipment", e.getMessage());
			message=e.getMessage();
			return "failure";
		}
	}
	public String delEquipment()
	{
		try
		{
			if(this.equipmentNo.equals("0"))
			{
				message="Please Select a Equipment!!";
				return "failure";
			}
			BioMedicalManager bioMedicalManager=new BioMedicalManager();
			bioMedicalManager.delEquipment(equipmentNo);
			
			this.idList=bioMedicalManager.getIds();
			message="Equipment "+equipmentNo+" deleted successfully";
			return "success";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "delEquipment", e.getMessage());
			message=e.getMessage();
			return "failure";
		}
	}
	
	public String updateEqp()
	{

		try
		{
			message="";
			if(this.equipmentNo.equals("0"))
			{
				message="Please Select a Equipment!!";
				return "failure";
			}
			
			BioMedicalManager bioMedicalManager=new BioMedicalManager();
			
			this.equipmentNo=bioMedicalManager.updateEqp(this.equipmentTO1.get(0))
			;
			
			this.idList=bioMedicalManager.getIds();
			message="Equipment "+equipmentNo+" updated successfully";
			
			return "success";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "updateEqp", e.getMessage());
			message=e.getMessage();
			return "failure";
		}
	}
	public void getTO(ValueChangeEvent e)
	{
		try
		{
			message="";
		BioMedicalManager bioMedicalManager=new BioMedicalManager();
		this.equipmentNo=e.getNewValue().toString();
		this.equipmentTO1=bioMedicalManager.getTO(equipmentNo);
		if(this.equipmentNo.equals("0"))
		{
			message="Please Select a Equipment!!";
		}
		
		}
		catch(Exception ex)
		{
			ErrorLogger.logError(this.getClass().getName(), "getTO", ex.getMessage());
			message=ex.getMessage();
			
		}
	}
}
