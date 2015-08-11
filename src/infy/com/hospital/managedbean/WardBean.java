package infy.com.hospital.managedbean;


import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.WardManager;
import infy.com.hospital.to.WardTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

public class WardBean {

	private WardTO wardTO=new WardTO();
	private List<WardTO> listWardTO=new ArrayList<WardTO>();
	private String message;
	private List<SelectItem> idList=new ArrayList<SelectItem>();
	private String wardNo;
	private int status;
	private int mstatus;


	public WardBean()
	{
		try {
			WardManager wm=new WardManager();
			idList=wm.getids();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"WardBean",e.getMessage());
			this.message=e.getMessage();
		}

	}

	//	public String add()
	//	{
	//		
	//	}
	//	
	//	public String delete()
	//	{
	//		
	//	}
	//	
	//	public String modify()
	//	{
	//		
	//	}
	//	
	//	public String getA()
	//	{
	//		
	//	}
	//	public void setA(String a)
	//	{
	//		
	//	}
	//	
	//	public String getD()
	//	{
	//		
	//	}
	//	public void setD(String d)
	//	{
	//		
	//	}
	//	
	//	public String getM()
	//	{
	//		
	//	}
	//	public void setM(String m)
	//	{
	//		
	//	}

	public void getlink(ActionEvent e)
	{

		this.wardNo=null;
		this.status=0;
		this.message=null;
		this.mstatus=0;	
		if(e.getComponent().getId().equalsIgnoreCase("add"))
		{
			this.status=1;
		}
		else if(e.getComponent().getId().equalsIgnoreCase("delete"))
		{

			this.status=2;
		}
		else if(e.getComponent().getId().equalsIgnoreCase("modify"))
		{
			this.status=3;
		}
		this.wardTO=new WardTO();
	}

	public void getTO(ValueChangeEvent e)
	{
		try {
			//this.listWardTO=null;
			this.message=null;
			wardNo=e.getNewValue().toString();
			WardManager wm=new WardManager();
			listWardTO=wm.getTO(wardNo);
			if(wardNo.equalsIgnoreCase("0"))
			{
				this.mstatus=0;			
				this.message="Please select Ward No to be modified";
			}
			else
			{	
				this.mstatus=1;	
				wardTO=listWardTO.get(0);
			}
		} catch (Exception e2) {
			ErrorLogger.logError(this.getClass().getName(),"getTO",e2.getMessage());
			this.message=e2.getMessage();
		}

	}


	public void noofbedsvalidate(FacesContext arg0, UIComponent arg1, Object noofbeds)
	throws ValidatorException
	{
		this.message=null;
		Integer i1=(Integer) noofbeds;
		String name1=noofbeds.toString();
		if(i1<0)
		{
			FacesMessage fm=new FacesMessage();
			fm.setSummary("No Of Beds Cannot be negative");
			fm.setDetail("No Of Beds Cannot be negative");
			throw new ValidatorException(fm);
		}
		if(name1.length()>5)
		{
			FacesMessage fm=new FacesMessage();
			fm.setSummary("No Of Beds Cannot exceed the size 5");
			fm.setDetail("No Of Beds Cannot exceed the size 5");
			throw new ValidatorException(fm);
		}
			
		}
		public void wardtypevalidate(FacesContext arg0, UIComponent arg1, Object wardtype)
		throws ValidatorException
		{
			String type=wardtype.toString();
			this.message=null;
			if(!(type.equalsIgnoreCase("GEN") || type.equalsIgnoreCase("PRI") ))
			{
				FacesMessage fm=new FacesMessage();
				fm.setSummary("Ward Type Cannot be other than GEN or PRI");
				fm.setDetail("Ward Type Cannot be other than GEN or PRI");
				throw new ValidatorException(fm);
			}
		}


		public void wardstatusvalidate(FacesContext arg0, UIComponent arg1, Object wardstatus)
		throws ValidatorException
		{
			String type=wardstatus.toString();
			this.message=null;
			if(!(type.equalsIgnoreCase("OC") || type.equalsIgnoreCase("FR") ))
			{
				FacesMessage fm=new FacesMessage();
				fm.setSummary("Ward Status Cannot be other than OC or FR");
				fm.setDetail("Ward Status Cannot be other than OC or FR");
				throw new ValidatorException(fm);
			}

		}


		public void pricevalidate(FacesContext arg0, UIComponent arg1, Object price)
		throws ValidatorException
		{
			Double i1=(Double) price;
			String name=price.toString();
			int j=0;
			this.message=null;
			if(i1<0)
			{
				FacesMessage fm=new FacesMessage();
				fm.setSummary("Price Cannot be less than Zero");
				fm.setDetail("Price Cannot be less than Zero");
				throw new ValidatorException(fm);
			}			
            
            for(int i=0;i<name.length();i++)
            {
            if((name.charAt(i))!='.')
            {
                            j++;
                            
            }
            }
            if(j>12)
            {
                            FacesMessage message=new FacesMessage();
                            message.setSummary("Price length cannot be greater than 10 digits");
                            message.setDetail("Price length cannot be greater than 10 digits");
                            throw new ValidatorException(message);
            }

		
		}

		public String addWard() 
		{			
			try {		
				this.message=null;
				WardManager wm=new WardManager();
				wardTO.setWStatus("FR");
				if(wardTO.getWType().equalsIgnoreCase("GEN") && wardTO.getNoOfBeds()>20)
				{
					this.message="No Of Beds Cannot be greater than 20 for Ward type 'General'";
					//this.wardTO=new WardTO();
					return null;
				}
				else
				{
					wardNo=wm.addWard(wardTO);
					idList=wm.getids();
					this.message="Ward  "+wardNo+"  has been added successfully";
				}
			}

			catch (Exception e) {
				ErrorLogger.logError(this.getClass().getName(),"addWard",e.getMessage());
				this.message=e.getMessage();
			}
			return null;			
		}
		public String delWard()
		{		
			try {
				WardManager wm=new WardManager();
				if(wardNo.equalsIgnoreCase("0"))
				{
					this.wardTO=null;
					this.message="Please select Ward No to be deleted";
				}
				else
				{				
					wardNo=wm.delWard(wardNo);
					if(wardNo.equalsIgnoreCase("1"))
					{
						this.message="Ward is not Empty...Cannot Delete Ward!!! ";
					}
					else
					{
						idList=wm.getids();
						this.message="Ward  "+wardNo+"  has been deleted successfully";
					}
				}
			} catch (Exception e) {
				ErrorLogger.logError(this.getClass().getName(),"delWard",e.getMessage());
				this.message=e.getMessage();	
			}
			return null;
		}
		public String updateWard()
		{
			try {
				WardManager wm=new WardManager();				
				if(wardTO.getWType().equalsIgnoreCase("GEN") && wardTO.getNoOfBeds()>20)
				{					
					this.message="No Of Beds Cannot be greater than 20 for Ward type 'General'";
					return null;				
				}
				if(wardTO.getWStatus().equalsIgnoreCase("FR"))
				{					
					this.message="Ward is already occupied..it cannot be freed";
					return null;				
				}
				if(wardNo.equalsIgnoreCase("0"))
				{			
					this.message="Please select Ward No to be modified";
					return null;
				}
				wardNo=wm.updateWard(wardTO);
				idList=wm.getids();
				this.message="Ward  "+wardNo+"  has been modified successfully";

			} catch (Exception e) {
				ErrorLogger.logError(this.getClass().getName(),"updateWard",e.getMessage());
				this.message=e.getMessage();	
			}
			return null;
		}

		public WardTO getWardTO() {
			return wardTO;
		}

		public void setWardTO(WardTO wardTO) {
			this.wardTO = wardTO;
		}

		public List<WardTO> getListWardTO() {
			return listWardTO;
		}

		public void setListWardTO(List<WardTO> listWardTO) {
			this.listWardTO = listWardTO;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public List<SelectItem> getIdList() {
			return idList;
		}

		public void setIdList(List<SelectItem> idList) {
			this.idList = idList;
		}

		public String getWardNo() {
			return wardNo;
		}

		public void setWardNo(String wardNo) {
			this.wardNo = wardNo;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public int getMstatus() {
			return mstatus;
		}

		public void setMstatus(int mstatus) {
			this.mstatus = mstatus;
		}


		//	public String getErrorMessage()
		//	{
		//		
		//	}
		//	
		//	public void setErrorMessage(String errorMessage)
		//	{
		//		
		//	}



	}
