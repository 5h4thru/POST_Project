package infy.com.hospital.manager;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.TariffService;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class TariffManager {
	public List<SelectItem> getDoctors() throws Exception
	{
		List <SelectItem> list=new ArrayList<SelectItem>();
		try
		{
			list=new TariffService().getDoctors();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(),"getDoctorsManager", e.getMessage());
			throw e;
		}
		return list;
	}
	public List<SelectItem> getWards() throws Exception
	{
		List <SelectItem> list=new ArrayList<SelectItem>();
		try
		{
			list=new TariffService().getWards();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(),"getWardsManager", e.getMessage());
			throw e;
		}
		return list;
	}
	public List<SelectItem> getOts() throws Exception
	{
		List <SelectItem> list=new ArrayList<SelectItem>();
		try
		{
			list=new TariffService().getOts();
			
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(),"getOtsManager", e.getMessage());
			throw e;
		}
		return list;
	}
	
	public Integer docCost(String doctorId) throws Exception
	{
		int cost=0;
		try
		{
			cost=new TariffService().docCost(doctorId);
			
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(),"docCostManager", e.getMessage());
			throw e;
		}
		return cost;
	}
	public double wardCost(int wardNo) throws Exception
	{
		double cost=0.0;
		try
		{
			cost=new TariffService().wardCost(wardNo);
			
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(),"wardCostManager", e.getMessage());
			throw e;
		}
		return cost;
	}
	public double otCost(int otNo) throws Exception
	{
		double cost=0.0;
		try
		{
			cost=new TariffService().otCost(otNo);
			
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(),"oTCostManager", e.getMessage());
			throw e;
		}
		return cost;
		
	}
	
	
	
}
