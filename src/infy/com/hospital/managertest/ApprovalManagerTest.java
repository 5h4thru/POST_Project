package infy.com.hospital.managertest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import infy.com.hospital.exception.NoProfileRequests;
import infy.com.hospital.manager.ApprovalManager;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.ProfileChangeRequestTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.RollbackException;

import org.junit.Test;



public class ApprovalManagerTest {

//	@Test
//	public void testGetProRequest() throws Exception{
//		ApprovalManager manager = new ApprovalManager();
//		manager.getProRequests();
//	}
	
	@Test(expected=NoProfileRequests.class)
	public void failTestGetProRequest() throws Exception{
		ApprovalManager manager = new ApprovalManager();
		manager.getProRequests();
	}
	
	@Test
	public void updateAcceptProfile() throws Exception{
		ApprovalManager manager = new ApprovalManager();
		List<ProfileChangeRequestTO> list = new ArrayList<ProfileChangeRequestTO>();
		ProfileChangeRequestTO pcTO = new ProfileChangeRequestTO();
		pcTO.setAddress("Mysore Chennai");
		pcTO.setConsultationFees(123);
		pcTO.setDepartment("operation");
		pcTO.setDoctorId("DC101");
		pcTO.setEmployeeName("Rahul");
		pcTO.setPhoneNo(123654L);
		pcTO.setQualification("MBBS");
		pcTO.setRequestStatus('A');
		list.add(pcTO);
		manager.updateAcceptProfile(list);
	}
	
	@Test(expected=RollbackException.class)
	public void failupdateAcceptProfile() throws Exception{
		ApprovalManager manager = new ApprovalManager();
		List<ProfileChangeRequestTO> list = new ArrayList<ProfileChangeRequestTO>();
		ProfileChangeRequestTO pcTO = new ProfileChangeRequestTO();
		list.add(pcTO);
		manager.updateAcceptProfile(list);
	}
	
	
	@Test
	public void updateRejecttProfile() throws Exception{
		ApprovalManager manager = new ApprovalManager();
		List<ProfileChangeRequestTO> list = new ArrayList<ProfileChangeRequestTO>();
		ProfileChangeRequestTO pcTO = new ProfileChangeRequestTO();
		pcTO.setAddress("Mysore Chennai");
		pcTO.setConsultationFees(123);
		pcTO.setDepartment("operation");
		pcTO.setDoctorId("DC101");
		pcTO.setEmployeeName("Rahul");
		pcTO.setPhoneNo(123654L);
		pcTO.setQualification("MBBS");
		pcTO.setRequestStatus('R');
		list.add(pcTO);
		manager.updateAcceptProfile(list);
	}
	
	@Test(expected=RollbackException.class)
	public void failupdateRejectProfile() throws Exception{
		ApprovalManager manager = new ApprovalManager();
		List<ProfileChangeRequestTO> list = new ArrayList<ProfileChangeRequestTO>();
		ProfileChangeRequestTO pcTO = new ProfileChangeRequestTO();
		list.add(pcTO);
		manager.updateAcceptProfile(list);
	}

	

	@Test
	public void testGetDrugRequests() throws Exception
	{
		List<DrugRequestTO> list=new ArrayList<DrugRequestTO>();
	ApprovalManager am=new ApprovalManager();
		list=am.getDrugRequests();
		assertTrue("Invalid",list!=null);		
	}
	
	@Test
	public void failtestGetDrugRequests() throws Exception	{
		List<DrugRequestTO> list=new ArrayList<DrugRequestTO>();
		ApprovalManager am=new ApprovalManager();
		am.getDrugRequests();
		assertFalse("Invalid",list==null);
	}

	@Test
	public  void testUpdateAcceptDrug() throws Exception 
	{
		List<DrugRequestTO> drugUpdateList=new ArrayList<DrugRequestTO>();
		ApprovalManager am=new ApprovalManager();
		DrugRequestTO dto=new DrugRequestTO();
		dto.setDoctorId("DC101");
		dto.setDrugName("Crocin");
		dto.setQuantity(200);
		dto.setManufacturer("Ranbaxy");
		dto.setCostPerUnit(20);
		Date d=new Date("12-Apr-2023");
		dto.setDateOfExpiry(d);
		dto.setUrgent("UG");
		dto.setSelected(true);	
		drugUpdateList.add(dto);
		am.updateAcceptDrug(drugUpdateList);
	}

	
	@Test(expected=Exception.class)
	public void failtestUpdateAcceptDrug() throws Exception 
	{
		List<DrugRequestTO> drugUpdateList=new ArrayList<DrugRequestTO>();
		ApprovalManager am=new ApprovalManager();
		DrugRequestTO dto=new DrugRequestTO();
		drugUpdateList.add(dto);
		am.updateAcceptDrug(drugUpdateList);
	}
	@Test
	public  void testUpdateRejectDrug() throws Exception
	{
		List<DrugRequestTO> drugUpdateList=new ArrayList<DrugRequestTO>();
		ApprovalManager am=new ApprovalManager();
		DrugRequestTO dto=new DrugRequestTO();
		dto.setDoctorId("DC101");
		dto.setDrugName("Crocin");
		dto.setQuantity(200);
		dto.setManufacturer("Ranbaxy");
		dto.setCostPerUnit(20);
		Date d=new Date("12-Apr-2023");
		dto.setDateOfExpiry(d);
		dto.setUrgent("UG");
		dto.setSelected(true);	
		drugUpdateList.add(dto);
		am.updateRejectDrug(drugUpdateList);
	}
	
	@Test(expected=Exception.class)
	public  void failtestUpdateRejectDrug() throws Exception
	{
		List<DrugRequestTO> drugUpdateList=new ArrayList<DrugRequestTO>();
		ApprovalManager am=new ApprovalManager();
		DrugRequestTO dto=new DrugRequestTO();
		drugUpdateList.add(dto);
		am.updateRejectDrug(drugUpdateList);
	}

}