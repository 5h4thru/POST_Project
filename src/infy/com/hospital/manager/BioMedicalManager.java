package infy.com.hospital.manager;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.BioMedicalService;
import infy.com.hospital.to.BiomedicalEquipmentTO;

import java.util.List;

import javax.faces.model.SelectItem;

public class BioMedicalManager {
	public String addEquipment(BiomedicalEquipmentTO biomedicalEquipmentTO) throws Exception
	{
		try
		{
			BioMedicalService bioMedicalService=new BioMedicalService();
			return bioMedicalService.addEquipment(biomedicalEquipmentTO);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "addEquipment", e.getMessage());
			throw e;
		}
	}
	public List<SelectItem> getIds() throws Exception
	{
		
		try
		{
			
			BioMedicalService bioMedicalService=new BioMedicalService();
			return bioMedicalService.getIds();
		}
		catch(Exception e)
		
		{
			ErrorLogger.logError(this.getClass().getName(), "getIds", e.getMessage());
			throw e;
		}
	}
	public String delEquipment(String equipmentNo) throws Exception
	{
		try
		{
			BioMedicalService bioMedicalService=new BioMedicalService();
			return bioMedicalService.delEquipment(equipmentNo);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "delEquipment", e.getMessage());
			throw e;
		}
	}
	public List<BiomedicalEquipmentTO> getTO(String equipmentNo) throws Exception
	{
		try
		{
			BioMedicalService bioMedicalService=new BioMedicalService();
			return bioMedicalService.getTO(equipmentNo);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "getTO", e.getMessage());
			throw e;
		}
	}
	public String updateEqp(BiomedicalEquipmentTO biomedicalEquipmentTO) throws Exception
	{
		try
		{
			BioMedicalService bioMedicalService=new BioMedicalService();
			return bioMedicalService.updateEqp(biomedicalEquipmentTO);
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(), "updateEqp", e.getMessage());
			throw e;
		}
	}


}
