package infy.com.hospital.managedbean;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.BillingManager;
import infy.com.hospital.to.BillingTO;

import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

public class BillingBean {
	private String registrationNo;
	private String patientId;
	private Double amount;
	private String message;
	private String billNo;
	private List<BillingTO> list;
	private Date billingDate=new Date();
	private String description;
	private double balance;
	private double advance;
	private double total;
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public String getRegistrationNo() {
		return registrationNo;
	}
	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public List<BillingTO> getList() {
		return list;
	}
	public void setList(List<BillingTO> list) {
		this.list = list;
	}
	public Date getBillingDate() {
		return billingDate;
	}
	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getAdvance() {
		return advance;
	}
	public void setAdvance(double advance) {
		this.advance = advance;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String payIPD()
	{
		try{
			BillingTO billingTO=new BillingManager().payIPD(registrationNo, patientId, amount);
			this.amount=billingTO.getAmount();
			this.billingDate=billingTO.getBillingDate();
			this.billNo=billingTO.getBillNo();
			this.description=billingTO.getDescription();
			return "successPayment";
		}
		catch (Exception e) {
			ErrorLogger.logError(e.getClass().getName(),"payIPDBean", e.getMessage());
			this.message=e.getMessage();
			return "failure";
		}
	}
	public String discharge()
	{
	
		try
		{
			this.amount=new BillingManager().discharge(registrationNo, patientId);
			if(amount>=0.0)
			{
				if(amount==0.0)
					this.message="Patient discharged successfully.";
				else
					this.message="Patient has paid "+ amount.doubleValue() +" Rs extra. Please Refund this amount to patient";
				FacesContext ctx=FacesContext.getCurrentInstance();
	            ExternalContext etx=ctx.getExternalContext();
	            HttpSession session=(HttpSession)etx.getSession(true);
	            session.setAttribute("s_regNo",registrationNo);
	            session.setAttribute("s_msg", message);
	            return "successDischarge";
			}
			else
			{
				this.message="Patient can not be discharged. Patient has to pay "+amount.doubleValue()+"Rs more";
				this.amount=null;
				return "failure";
			}
			
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(),"discharge", e.getMessage());
			this.message=e.getMessage();
			return "failure";
		}
	}
	public String generateBill()
	{
		try
		{
			this.list=new BillingManager().generateBill(registrationNo, patientId);
			if(list.size()==0)
			{
				this.message="No Bills for the requested Patient";
				return "failure";
			}
			this.advance=0.0;
			this.total=0.0;
			for(int i=0;i<list.size();i++)
			{
				BillingTO billingTO=list.get(i);
				if(billingTO.getDescription().equals("Advance"))
					this.advance=this.advance+billingTO.getAmount();
				else
					this.total=this.total+billingTO.getAmount();
			}
			this.balance=this.total-this.advance;
			this.message="where advance is the amount paid by the patient and total are towards the others expenses llike medicine purchase etc.,";
			return "successGenerate";
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			this.message=e.getMessage();
			return "failure";
		}
	}
	

}
