package infy.com.hospital.managedbean;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.DrugManager;
import infy.com.hospital.to.DrugTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class DrugBean {

	private DrugTO drugTO;
	private List<DrugTO> listDrugTO;
	private static String message;
	private List<SelectItem> idList;
	private String drugNo;
	private String status;
	
	
	public DrugBean()
	{
		this.status="";
		message="";
		this.idList=new ArrayList<SelectItem>();
		this.listDrugTO=new ArrayList<DrugTO>();
		drugTO=new DrugTO();
		try
		{
		DrugManager drugManager=new DrugManager();
		this.idList=drugManager.getIds();
		}
		catch(Exception e)
		{
			message=e.getMessage();
		}
	}

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
	public String addDrug()
	{
		try
		{
		message="";
		DrugManager drugManager=new DrugManager();
		this.drugNo=drugManager.addDrug(drugTO);
		this.idList=drugManager.getIds();
		message="Drug "+drugNo+" added successfully";
		return "success";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "addDrug", e.getMessage());
			message=e.getMessage();
			return "fail";
		}
	}
	
	public String navigate()
	{
		if(drugTO.getDrugName()!=null)
		{
			drugTO.setDrugNo(null);
			drugTO.setCostPerUnit(null);
			drugTO.setCompany(null);
			drugTO.setQuantity(null);
			drugTO.setDateOfExpiry(null);
			drugTO.setDrugName(null);
		}
		
		this.status ="add";
		message="";
		this.drugNo="0";
		
		return null;
		
	}
	public String navigate1()
	{
		
		this.status ="del";
	message="";
		this.drugNo="0";
		
		return null;
	}
	public String navigate2()
	{
		
		this.status ="modify";
		message="";
		this.drugNo="0";
		
		this.listDrugTO=null;
		return null;
	}
	
	public String delDrug()
	{
		try
		{
			message="";
			if(this.drugNo.equals("0"))
			{
				message="Please Select a Drug!!";
				return "failure";
			}
			
			
			DrugManager drugManager=new DrugManager();
			
			drugManager.delDrug(drugNo);
			this.idList=drugManager.getIds();
			
		message="Drug "+drugNo+" deleted successfully";
			return "success";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "delDrug", e.getMessage());
			message=e.getMessage();
			return "failure";
		}
	}
	public void getTO(ValueChangeEvent e)
	{
		
		try
		{
		
		message="";
		DrugManager drugManager=new DrugManager();
	 
		this.drugNo=e.getNewValue().toString();
		
		
		
		this.listDrugTO=drugManager.getTO(drugNo);
		if(this.drugNo.equals("0"))
		{
			message="Please Select a Drug!!";
		}
		
		}
		catch(Exception ex)
		{
			ErrorLogger.logError(this.getClass().getName(), "getTO", ex.getMessage());
			message=ex.getMessage();
			
		}
		
	}
	public String updateDrug()
	{
		try
		{
			
			message="";
			if(this.drugNo.equals("0"))
			{
				message="Please Select a Drug!!";
				return "failure";
			}
			
			
			
			DrugManager drugManager=new DrugManager();
			drugManager.updateDrug(this.listDrugTO.get(0));
			this.idList=drugManager.getIds();
			
			message="Updated successfully";
			
			return "success";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "updateDrug", e.getMessage());
			message=e.getMessage();
			return "failure";
		}
		
	}
	
	public DrugTO getDrugTO() {
		return drugTO;
	}
	public void setDrugTO(DrugTO drugTO) {
		this.drugTO = drugTO;
	}
	public List<DrugTO> getListDrugTO() {
		return listDrugTO;
	}
	public void setListDrugTO(List<DrugTO> listDrugTO) {
		this.listDrugTO = listDrugTO;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message1) {
		message = message1;
	}
	public List<SelectItem> getIdList() {
		return idList;
	}
	public void setIdList(List<SelectItem> idList) {
		this.idList = idList;
	}
	public String getDrugNo() {
		return drugNo;
	}
	public void setDrugNo(String drugNo) {
		this.drugNo = drugNo;
	}
	
	
}
