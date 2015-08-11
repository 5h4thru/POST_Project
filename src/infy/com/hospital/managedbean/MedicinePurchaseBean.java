package infy.com.hospital.managedbean;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.MedicinePurchaseManager;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.DrugTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

public class MedicinePurchaseBean 

{

	private String registrationNo;
	private int quantity=1;
	private String medicineName;
	private List<SelectItem> drugList;
	private List<DrugTO> list;
	private String message;
	private Integer price=0;
	private String userName;
	private String patientType;
	private List<DrugRequestTO> listDrug=new ArrayList<DrugRequestTO>();
	
	public MedicinePurchaseBean()
	{
		drugList=new ArrayList<SelectItem>();
		list=new ArrayList<DrugTO>();
		//listDrug=new ArrayList<DrugRequestTO>();
		try
		{
			FacesContext c=FacesContext.getCurrentInstance();
            HttpSession session=(HttpSession)c.getExternalContext().getSession(true);
            this.userName=(String) session.getAttribute("uname");

		MedicinePurchaseManager mpm=new MedicinePurchaseManager();
		this.list=mpm.listOfDrugs();
		for (DrugTO d : this.list) 
		{
			SelectItem s=new SelectItem(d.getDrugName(),d.getDrugName().toString());
			this.drugList.add(s);
		}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"MedicinePurchaseBean",e.getMessage());
			this.message=e.getMessage();
		}
	}
	
	public void medicinePrice(ValueChangeEvent e)
	{
		//Date d=new Date();
		this.message="";
		try
		{
		this.medicineName=(String)e.getNewValue();
		for (DrugTO dt : this.list) 
		{
			if(dt.getDrugName().equals(this.medicineName))
			{
				this.price=dt.getCostPerUnit();
				//d=dt.getDateOfExpiry();
			}	
		}
//		if(d.before(new Date()))
//		{
//			this.message="Please select another drug.This drug is expired";
//		}
		}
		catch(Exception e1)
		{
			ErrorLogger.logError(this.getClass().getName(),"MedicinePrice",e1.getMessage());
			this.message=e1.getMessage();
		}
		
	}
	
	public String makePayment()
	{
		try
		{
			if(this.listDrug.isEmpty())
			{
				this.message="No medicines added to your list.First Click on Add and then on Make Payment";
				return "fail";
			}
			else
			{
			if(this.patientType.equals("OPD"))
			{
			double amount=0;
			String billNo=null;
			for (DrugRequestTO drt : this.listDrug) 
			{
				amount=amount+drt.getQuantity()*drt.getCostPerUnit();
				billNo=new MedicinePurchaseManager().makePayment(drt);
				
			}
			this.message="Bill No "+billNo+"\n Total Bill Amount is "+amount+" for Patient Registration No. "+this.registrationNo;
			this.listDrug.clear();
			return "success";
			}
			else 
			{
				String billNo=null;
				for (DrugRequestTO drt : this.listDrug) 
				{
					//amount=amount+drt.getQuantity()*drt.getCostPerUnit();
					billNo=new MedicinePurchaseManager().makePayment(drt);
					
//					System.out.println(billNo);
				}
				this.message="Details added to Customer Bill Successfully";
				this.listDrug.clear();
				return "fail";
			}
			}
		}
		catch(Exception e1)
		{
			ErrorLogger.logError(this.getClass().getName(),"makePayment",e1.getMessage());
			this.message=e1.getMessage();
			this.listDrug.clear();
			return "fail";
		}
	}
	
	public String addMedicine()
	{
		try
		{
		DrugRequestTO drt=new DrugRequestTO();
		drt.setRegistrationNo(this.registrationNo);
		drt.setQuantity(this.quantity);
		drt.setDrugName(this.medicineName);
		drt.setPatientType(this.patientType);
		drt.setCostPerUnit(this.price);
		Date d=new Date();
		for (DrugTO dt : this.list) 
		{
			if(dt.getDrugName().equals(this.medicineName))
			{
				//this.price=dt.getCostPerUnit();
				d=dt.getDateOfExpiry();
			}	
		}
		if(d.before(new Date()))
		{
			this.message="Drug is expired";
			return "fail";
		}
		else
		{
		MedicinePurchaseManager mpm=new MedicinePurchaseManager();
		mpm.validatePatientDetails(this.patientType,this.registrationNo);
		this.listDrug.add(drt);
		this.message="Drug "+this.medicineName+" added successfully to the list";
		return "success";
		}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"addMedicine",e.getMessage());
			this.message=e.getMessage();
			return "fail";
		}
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public List<SelectItem> getDrugList() {
		return drugList;
	}

	public void setDrugList(List<SelectItem> drugList) {
		this.drugList = drugList;
	}

	public List<DrugTO> getList() {
		return list;
	}

	public void setList(List<DrugTO> list) {
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPatientType() {
		return patientType;
	}

	public void setPatientType(String patientType) {
		this.patientType = patientType;
	}

	public List<DrugRequestTO> getListDrug() {
		return listDrug;
	}

	public void setListDrug(List<DrugRequestTO> listDrug) {
		this.listDrug = listDrug;
	}
	
	
}
