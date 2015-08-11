package infy.com.hospital.manager;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.WardService;
import infy.com.hospital.to.WardTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

public class WardManager {
	public String addWard(WardTO wardTO)throws Exception
	{
		try {
			WardService ws=new WardService();
			String wardno=ws.addWard(wardTO);
			return wardno;
		}
			catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"addward",e.getMessage());
			throw e;
		}
		
	}
	public List<SelectItem> getids()throws Exception
	{
		try {			
			List<SelectItem> list1=new ArrayList<SelectItem>();
			WardService ws=new WardService();
			list1=ws.getids();
			return list1;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getids",e.getMessage());
			throw e;
		}
	}
	
	public String delWard(String wardNumber)throws Exception
	{
		try {
			WardService ws=new WardService();
			String wardno=ws.delWard(wardNumber);
			return wardno;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"delward",e.getMessage());
			throw e;
		}
	}
	
	public List<WardTO> getTO(String wardNo) throws Exception
	{
		try {
			List<WardTO> list1=new ArrayList<WardTO>();
			WardService ws=new WardService();
			list1=ws.getTO(wardNo);
			return list1;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getTO",e.getMessage());
			throw e;
		}
	}
	
	public String updateWard(WardTO wardTO)throws Exception
	{
		try {
			WardService ws=new WardService();
			String wardno=ws.updateWard(wardTO);
			return wardno;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"updateWard",e.getMessage());
			throw e;
		}
	}
}
