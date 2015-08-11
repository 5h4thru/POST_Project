package infy.com.hospital.managedbean;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.TariffManager;

import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

public class TariffBean {
	private String doctorId;
	private String wardType;
	private String docMsg;
	private String wardMsg;
	private String otMsg;
	private String msg;
	private List<SelectItem> doctors;
	private List<SelectItem> wards;
	private List<SelectItem> ots;
	
	
	public TariffBean()
	{
		try
		{
			TariffManager tm=new TariffManager();
			doctors=tm.getDoctors();
			wards=tm.getWards();
			ots=tm.getOts();
			this.docMsg=" select the doctor.";
			this.wardMsg=" select the ward.";
			this.otMsg=" select the operation Theatre.";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "TariffBean Constructor", e.getMessage());
			this.msg=e.getMessage();
		}
	}
	public void docCost(ValueChangeEvent event)
	{
		String doctorId=(String)event.getNewValue();
		try
		{
			int cost=new TariffManager().docCost(doctorId);
			this.docMsg="Consultation Fee: "+cost+" Rs.";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "docCostBean", e.getMessage());
			this.docMsg=e.getMessage();
		}
	}
	public void wardCost(ValueChangeEvent event)
	{
		int wardNo=Integer.parseInt((String)event.getNewValue());
		try
		{
			double cost=new TariffManager().wardCost(wardNo);
			this.wardMsg="Ward cost: "+cost+" Rs.";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "wardCostBean", e.getMessage());
			this.wardMsg=e.getMessage();
		}
		
	}
	public void otCost(ValueChangeEvent event)
	{
		int otNo=Integer.parseInt((String)event.getNewValue());
		try
		{
			double cost=new TariffManager().otCost(otNo);
			this.otMsg="Operation Theatre cost: "+cost+" Rs.";
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "otCostBean", e.getMessage());
			this.otMsg=e.getMessage();
		}
		
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}
	public String getWardType() {
		return wardType;
	}
	public void setWardType(String wardType) {
		this.wardType = wardType;
	}
	public String getDocMsg() {
		return docMsg;
	}
	public void setDocMsg(String docMsg) {
		this.docMsg = docMsg;
	}
	public String getWardMsg() {
		return wardMsg;
	}
	public void setWardMsg(String wardMsg) {
		this.wardMsg = wardMsg;
	}
	public String getOtMsg() {
		return otMsg;
	}
	public void setOtMsg(String otMsg) {
		this.otMsg = otMsg;
	}
	public List<SelectItem> getWards() {
		return wards;
	}
	public void setWards(List<SelectItem> wards) {
		this.wards = wards;
	}
	
	public List<SelectItem> getOts() {
		return ots;
	}
	public void setOts(List<SelectItem> ots) {
		this.ots = ots;
	}
	public List<SelectItem> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<SelectItem> doctors) {
		this.doctors = doctors;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
