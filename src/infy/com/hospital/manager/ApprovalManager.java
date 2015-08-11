package infy.com.hospital.manager;

import infy.com.hospital.exception.NoProfileRequests;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.service.ApprovalService;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.ProfileChangeRequestTO;

import java.util.ArrayList;
import java.util.List;

public class ApprovalManager {
	public List<ProfileChangeRequestTO> getProRequests() throws Exception,NoProfileRequests{
		ApprovalService service = new ApprovalService();
		List<ProfileChangeRequestTO> list = new ArrayList<ProfileChangeRequestTO>();
		try {
			list = service.getProRequests();
			if(list.size()==0)
				throw new NoProfileRequests();
			else
				return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getProRequests", e.getMessage());
			throw e;
		}
	}

	public void updateAcceptProfile(List<ProfileChangeRequestTO> profileUpdateList) throws Exception{
		ApprovalService service = new ApprovalService();
		try {
			service.updateAcceptProfile(profileUpdateList);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateAcceptProfile", e.getMessage());
			throw e;
		}
	}

	public void updateRejectProfile(List<ProfileChangeRequestTO> profileUpdateList) throws Exception{
		ApprovalService service = new ApprovalService();
		try {
			service.updateRejectProfile(profileUpdateList);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateRejectProfile", e.getMessage());
			throw e;
		}
	}

	
	
	public List<DrugRequestTO> getDrugRequests()throws Exception
	{
		try {	
			List<DrugRequestTO> list=new ArrayList<DrugRequestTO>();
			ApprovalService as=new ApprovalService();
			list=as.getDrugRequests();
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getDrugRequests",e.getMessage());
			throw e;
			
		}
		

	}

	public void updateAcceptDrug(List<DrugRequestTO> drugUpdateList) throws Exception
	{
		try {
			ApprovalService as=new ApprovalService();
			as.updateAcceptDrug(drugUpdateList);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"updateAcceptdrug",e.getMessage());
			throw e;
		}
		
	}
	public void updateRejectDrug(List<DrugRequestTO> drugUpdateList)throws Exception
	{
		try {
			ApprovalService as=new ApprovalService();
			as.updateRejectDrug(drugUpdateList);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"updateRejectptdrug",e.getMessage());
			throw e;
		}
	}

}