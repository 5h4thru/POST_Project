package infy.com.hospital.managertest;

import infy.com.hospital.exception.CannotDelete;
import infy.com.hospital.manager.EmployeeManager;
import infy.com.hospital.to.EmployeeTO;

import java.util.Date;

import javax.persistence.RollbackException;

import org.junit.Test;


public class EmployeeManagerTest {

	@Test
	public void addReceptionist() throws Exception{
		EmployeeManager manager = new EmployeeManager();
		EmployeeTO eto = new EmployeeTO();
		eto.setAddress("Japan");
		eto.setConsultationFees(123);
		eto.setDepartment("Surgery");
		eto.setDesignation("RE");
		eto.setEmployeeName("Rahul");
		eto.setPhoneNo(321654L);
		eto.setQualification("FRCS");
		eto.setSlot1("NA");
		eto.setSlot2("NA");
		eto.setSlot3("NA");
		manager.addReceptionist(eto);
	}
	
	@Test(expected=RollbackException.class)
	public void failAddReceptionist() throws Exception{
		EmployeeManager manager = new EmployeeManager();
		EmployeeTO eto = new EmployeeTO();
		manager.addReceptionist(eto);
	}
	
	
	@Test
	public void addDoctor() throws Exception{
		EmployeeManager manager = new EmployeeManager();
		EmployeeTO eto = new EmployeeTO();
		eto.setAddress("Japan");
		eto.setConsultationFees(123);
		eto.setDepartment("Surgery");
		eto.setDesignation("RE");
		eto.setEmployeeName("Rahul");
		eto.setPhoneNo(321654L);
		eto.setQualification("FRCS");
		eto.setSlot1("NA");
		eto.setSlot2("NA");
		eto.setSlot3("NA");
		manager.addDoctor(eto);
	}
	
	@Test(expected=RollbackException.class)
	public void failAddDoctor() throws Exception{
		EmployeeManager manager = new EmployeeManager();
		EmployeeTO eto = new EmployeeTO();
		manager.addDoctor(eto);
	}
	
	@Test
	public void getNames() throws Exception{
		EmployeeManager manager = new EmployeeManager();
		manager.getNames();
	}
	

	
	@Test
	public void getDocs() throws Exception{
		EmployeeManager manager = new EmployeeManager();
		manager.getDocs();
	}
	
	
	
//	@Test(expected=NoEmployees.class)
//	public void failGetDocs() throws Exception{
//		EmployeeManager manager = new EmployeeManager();
//		manager.getDocs();
//	}
//	
//	@Test(expected=NoEmployees.class)
//	public void failGetNames() throws Exception{
//		EmployeeManager manager = new EmployeeManager();
//		manager.getNames();
//	}
	
	
	
	@Test(expected=CannotDelete.class)
	public void failDelEmployee() throws Exception{
		EmployeeManager manager = new EmployeeManager();
		manager.delEmployee("DC111");
	}
	
//	@Test
//	public void delEmployee() throws Exception{
//		EmployeeManager manager = new EmployeeManager();
//		manager.delEmployee("DC101");
//	}
	
	@Test
	public void getTO() throws Exception{
		EmployeeManager manager = new EmployeeManager();
		manager.getTO("DC101");
	}
	
//	@Test
//	public void updateDoctor() throws Exception{
//		EmployeeManager manager = new EmployeeManager();
//		EmployeeTO eto = new EmployeeTO();
//		eto.setAddress("Japan");
//		eto.setUserName("DC102");
//		eto.setConsultationFees(123);
//		eto.setDepartment("Surgery");
//		eto.setDesignation("DC");
//		eto.setDateOfJoining(new Date());
//		eto.setEmployeeName("Rahul");
//		eto.setPhoneNo(321654L);
//		eto.setQualification("FRCS");
//		eto.setSlot1("NA");
//		eto.setSlot2("NA");
//		eto.setSlot3("NA");
//		manager.updateDoctor(eto);
//	}
	
	@Test(expected=RollbackException.class)
	public void failupdateDoctor() throws Exception{
		EmployeeManager manager = new EmployeeManager();
		EmployeeTO eto = new EmployeeTO();
		manager.updateDoctor(eto);
	}
}