package infy.com.hospital.manager;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.DrugService;
import infy.com.hospital.to.DrugTO;

import java.util.List;

import javax.faces.model.SelectItem;

public class DrugManager {
	public String addDrug(DrugTO drugTO) throws Exception
	{
		try
		{
		DrugService drugService=new DrugService();
		return drugService.addDrug(drugTO);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "addDrug", e.getMessage());
			throw e;
		}
	}
	
	public String delDrug(String drugNo) throws Exception
	{
		try
		{
			DrugService drugService=new DrugService();
			return drugService.delDrug(drugNo);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "delDrug", e.getMessage());
			throw e;
		}
	}
	public List<SelectItem> getIds() throws Exception
	{
		try
		{
			DrugService drugService=new DrugService();
			return drugService.getIds();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "getIds", e.getMessage());
			throw e;
		}
	}
	public List<DrugTO> getTO(String drugNo) throws Exception
	{
		try
		{
			DrugService drugService=new DrugService();
			return drugService.getTO(drugNo);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "getTO", e.getMessage());
			throw e;
		}
	}
	
	public String updateDrug(DrugTO drugTO) throws Exception
	{
		try
		{
			DrugService drugService=new DrugService();
			return drugService.updateDrug(drugTO);
		}
		catch(Exception e)
		
		{
			ErrorLogger.logError(this.getClass().getName(), "updateDrug", e.getMessage());
			throw e;
		}
	}

}
