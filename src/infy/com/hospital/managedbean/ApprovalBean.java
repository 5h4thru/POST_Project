package infy.com.hospital.managedbean;

import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.manager.ApprovalManager;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.ProfileChangeRequestTO;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

public class ApprovalBean {
	private List<ProfileChangeRequestTO> proRequests = new ArrayList<ProfileChangeRequestTO>();
	private ProfileChangeRequestTO changeRequestTO = new ProfileChangeRequestTO();
	private String message;
	private String message1;
	private List<DrugRequestTO> drugRequests=new ArrayList<DrugRequestTO>();
	private int mstatus;
	public int getMstatus() {
		return mstatus;
	}
	public void setMstatus(int mstatus) {
		this.mstatus = mstatus;
	}

	public List<DrugRequestTO> getDrugRequests() {
		return drugRequests;
	}

	public void setDrugRequests(List<DrugRequestTO> drugRequests) {
		this.drugRequests = drugRequests;
	}
	public ApprovalBean(){
		ApprovalManager manager = new ApprovalManager();
		try {
			this.message=null;
			showDForm();
			proRequests = manager.getProRequests();
			if(proRequests.size()==0)
				this.message = "No Profile Change Requests";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "ApprovalBean", e.getMessage());
			this.message = e.getMessage();
		}
	}
	
	public String getMessage1() {
		return message1;
	}
	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	public void showDForm()
	{
		try {
			ApprovalManager am=new ApprovalManager();
			drugRequests=am.getDrugRequests();
			if(drugRequests.size()==0)
			{				
				this.mstatus=0;
				this.message1="No More Requests pending to Approve/Reject";
			}
			else
			{
				this.mstatus=1;
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"showdform",e.getMessage());
			this.message1=e.getMessage();
		}

	}


	public void costperunitvalidate(FacesContext arg0, UIComponent arg1, Object price)
    throws ValidatorException
    {
          Integer i1=(Integer) price;
//        if(i1<0)
//        {
//              
//              FacesMessage fm=new FacesMessage();
//              fm.setSummary("Cost Per Unit Cannot be less than Zero");
//              fm.setDetail("Cost Per Unit Cannot be less than Zero");
//              throw new ValidatorException(fm);
//        }           
          if(i1>99999)
          {                 
                FacesMessage fm=new FacesMessage();
                fm.setSummary("Cost Per Unit Cannot exceed the size 5");
                fm.setDetail("Cost Per Unit Cannot exceed the size 5");
                throw new ValidatorException(fm);
          }
          


    }



	public String updateAcceptDrug()
	{
		try {
			this.message1=null;
			int count=drugRequests.size();
			ApprovalManager am=new ApprovalManager();
			List<DrugRequestTO> drugRequests1=new ArrayList<DrugRequestTO>();
			for(DrugRequestTO dto: drugRequests)
			{
				if(dto.isSelected())
				{
					drugRequests1.add(dto);
					count--;
					DrugRequestTO dto1=drugRequests1.get(0);
					if(dto1.getCostPerUnit()==null || dto.getDateOfExpiry()==null)
					{
						this.message1="Fields cannot be empty";
						return null;
					}
					else
					{						
						if(dto1.getCostPerUnit()<0)
						{
							if(dto.getDateOfExpiry().before(Calendar.getInstance().getTime()))
							{
								this.message1="cost Per unit cannot be negative and date is not valid!!";
								return null;
							}
							else
							{
								this.message1="cost Per unit cannot be negative!!";
								return null;
							}
						}
						else if(dto.getDateOfExpiry().before(Calendar.getInstance().getTime()))
						{
							this.message1="Date of Expiry should be greater than the current date";
							return null;
						}
					}
					//
					//drugRequests.remove(dto);
				}	

			}

			if(count==drugRequests.size())
			{
				this.message1="Select atleat one Checkbox";
			}
			else
			{
				am.updateAcceptDrug(drugRequests1);
				showDForm();
				this.message1="Request has been approved successfully";
			}


		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"updateAcceptDrug",e.getMessage());
			this.message1=e.getMessage();
		}
		return null;
	}
	public String updateRejectDrug()
	{
		try {
			this.message1=null;
			int count1=drugRequests.size();
			ApprovalManager am=new ApprovalManager();
			List<DrugRequestTO> drugRequests1=new ArrayList<DrugRequestTO>();
			for(DrugRequestTO dto: drugRequests)
			{
				if(dto.isSelected())
				{
					this.message1=null;
					drugRequests1.add(dto);
					count1--;
					DrugRequestTO dto1=drugRequests1.get(0);
					if(dto1.getCostPerUnit()==null || dto.getDateOfExpiry()==null)
					{
						this.message1="Fields cannot be empty";
						return null;
					}
					else
					{						
						if(dto1.getCostPerUnit()<0)
						{
							if(dto.getDateOfExpiry().before(Calendar.getInstance().getTime()))
							{
								this.message1="cost Per unit cannot be negative and date is not valid!!";
								return null;
							}
							else
							{
								this.message1="cost Per unit cannot be negative!!";
								return null;
							}
						}
						else if(dto.getDateOfExpiry().before(Calendar.getInstance().getTime()))
						{
							this.message1="Date of Expiry should be greater than the current date";
							return null;
						}
					}
				}	
			}
			if(count1==drugRequests.size())
			{
				this.message1="Select atleat one Checkbox";
			}
			else
			{
				am.updateRejectDrug(drugRequests1);
				showDForm();
				this.message1="Request has been rejected successfully";
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"updateRejectDrug",e.getMessage());
			this.message1=e.getMessage();
		}
		return null;
	}

//	public void getlink(ActionEvent e)
//	{
//		if(e.getComponent().getId().equalsIgnoreCase("profile"))
//		{
//			this.status=1;
//		}
//		else if(e.getComponent().getId().equalsIgnoreCase("drug"))
//		{
//			this.status=2;
//			showDForm();
//		}
//	}

	


	public String updateAcceptProfile() {
		ApprovalManager manager = new ApprovalManager();
		try {
			this.message=null;
			List<ProfileChangeRequestTO> tempList = new ArrayList<ProfileChangeRequestTO>();
			List<ProfileChangeRequestTO> newList = new ArrayList<ProfileChangeRequestTO>();
			for (int i = 0; i < proRequests.size(); i++) {
				if(proRequests.get(i).isSelected()){
					tempList.add(proRequests.get(i));
				}
				else{
					newList.add(proRequests.get(i));
				}
			}
			if(tempList.size()==0){
				this.message= "Please select at least one request";
				return "";
			}
			proRequests = newList;
			manager.updateAcceptProfile(tempList);
			manager.getProRequests();
			this.message = tempList.size()+" Profile change request(s) approved";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateAcceptProfile", e.getMessage());
			this.message = e.getMessage();
		}
		return "";
	}

	public String updateRejectProfile(){ 
		ApprovalManager manager = new ApprovalManager();
		try {
			this.message=null;
			List<ProfileChangeRequestTO> tempList = new ArrayList<ProfileChangeRequestTO>();
			List<ProfileChangeRequestTO> newList = new ArrayList<ProfileChangeRequestTO>();
			for (int i = 0; i < proRequests.size(); i++) {
				if(proRequests.get(i).isSelected()){
					tempList.add(proRequests.get(i));
				}
				else{
					newList.add(proRequests.get(i));
				}
			}
			if(tempList.size()==0){
				this.message= "Please select at least one request";
				return "";
			}
			proRequests = newList;
			manager.updateAcceptProfile(tempList);
			manager.getProRequests();
			this.message = tempList.size()+" Profile change request(s) rejected";
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateRejectProfile", e.getMessage());
			this.message = e.getMessage();
		}
		return "";
	}


	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ProfileChangeRequestTO> getProRequests() {
		return proRequests;
	}
	public void setProRequests(List<ProfileChangeRequestTO> proRequests) {
		this.proRequests = proRequests;
	}
	public ProfileChangeRequestTO getChangeRequestTO() {
		return changeRequestTO;
	}
	public void setChangeRequestTO(ProfileChangeRequestTO changeRequestTO) {
		this.changeRequestTO = changeRequestTO;
	}
}