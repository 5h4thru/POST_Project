package infy.com.hospital.managertest;

import static org.junit.Assert.assertTrue;
import infy.com.hospital.exception.InvalidPatientIdException;
import infy.com.hospital.exception.InvalidScheduleException;
import infy.com.hospital.exception.NoDoctorFoundException;
import infy.com.hospital.manager.DoctorManager;
import infy.com.hospital.to.DoctorTO;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.EmployeeTO;
import infy.com.hospital.to.SurgeryScheduleTO;

import java.util.Date;

import org.junit.Test;

public class DoctorManagerTest {

	@Test
	public final void testGetDepartmentList() throws Exception {
		DoctorManager manager = new DoctorManager();
		assertTrue("List of departments returned null", manager.getDepartmentList() != null);
	}

	@Test
	public final void testGetDoctor() throws NoDoctorFoundException, Exception {
		DoctorManager manager = new DoctorManager();
		assertTrue("List of doctors returned null", manager.getDoctor("DC102", "dermatologist", "S1") != null);
	}
	
	@Test(expected = NoDoctorFoundException.class)
	public final void testGetDoctorNoDoctor() throws NoDoctorFoundException, Exception {
		DoctorManager manager = new DoctorManager();
		manager.getDoctor("DC101", "dermatologist", "S1");
	}
	
	@Test
	public void testUpdateDoctorDetails() throws Exception {
		DoctorTO dto=new DoctorTO();
		dto.setConsultationFees(400);
		dto.setDepartment("oncology");
		dto.setUserName("DC101");
		
		EmployeeTO eto=new EmployeeTO();
		eto.setAddress("Street");
		eto.setEmployeeName("Ragu");
		eto.setPhoneNo(9940191194L);
		eto.setQualification("MBBS");
		
		DoctorManager dm=new DoctorManager();
		assertTrue("Update failed",dm.updateDoctorDetails(dto, eto)!=false);
		
		
	}

	@Test
	public void testGetDoctorDetails() throws Exception {
		DoctorManager dm=new DoctorManager();
		assertTrue("Doctor Details failed",dm.getDoctorDetails("DC102")!=null);
	}
	@Test
	public void testGetDoctorDetailsInvalid() throws Exception {
		DoctorManager dm=new DoctorManager();
		assertTrue("Doctor Details failed",dm.getDoctorDetails("900")==null);
	}
	@Test
	public void testGetEmployeeDetails() throws Exception {
		DoctorManager dm=new DoctorManager();
		assertTrue("Employee Details failed",dm.getEmployeeDetails("DC101")!=null);
	}
	@Test
	public void testGetEmployeeDetailsInvalid() throws Exception {
		DoctorManager dm=new DoctorManager();
		assertTrue("Doctor Details failed",dm.getEmployeeDetails("800")==null);
	}
	@Test
	public void testScheduleSurgery() throws InvalidScheduleException, Exception 
	{
		DoctorManager dm=new DoctorManager();
		SurgeryScheduleTO sst=new SurgeryScheduleTO();
		Date d=new Date();
		d.setDate(12);
		d.setMonth(4);
		d.setYear(117);
		sst.setDateOfSurgery(d);
		sst.setOtNo(2);
		sst.setPatientId("PA102");
		sst.setSlot("S2");
		sst.setSurgeryName("Plastic surgery");
		sst.setDoctorId("DC101");
		sst.setBookingStatus('B');
		dm.scheduleSurgery(sst);
	}

	@Test(expected=InvalidScheduleException.class)
	public void testInvalidScheduleSurgery() throws InvalidScheduleException, Exception 
	{
		DoctorManager dm=new DoctorManager();
		SurgeryScheduleTO sst=new SurgeryScheduleTO();
		Date d=new Date();
		d.setDate(12);
		d.setMonth(4);
		d.setYear(114);
		sst.setDateOfSurgery(d);
		sst.setOtNo(2);
		sst.setPatientId("PA102");
		sst.setSlot("S1");
		sst.setSurgeryName("Plastic surgery");
		sst.setDoctorId("DC101");
		sst.setBookingStatus('B');
		dm.scheduleSurgery(sst);
		//fail("Not yet implemented");
	}
	
	@Test
	public void testListOfOT() throws Exception 
	{
		DoctorManager dm=new DoctorManager();
		assertTrue(dm.listOfOT()!=null);
	}
//
	@Test
	public void testValidatePatientId() throws InvalidPatientIdException, Exception 
	{
		DoctorManager dm=new DoctorManager();
		assertTrue(dm.validatePatientId("PA101"));
		//fail("Not yet implemented");
	}
	@Test(expected=InvalidPatientIdException.class)
	public void testInvalidatePatientId() throws InvalidPatientIdException, Exception 
	{
		DoctorManager dm=new DoctorManager();
		dm.validatePatientId("PA105");
		//fail("Not yet implemented");
	}
	
    @Test
    public void testRequestMedicine() throws Exception{
          DrugRequestTO drugRequestTO=new DrugRequestTO();
          drugRequestTO.setManufacturer("NewLife");
          drugRequestTO.setDate(new Date());
          drugRequestTO.setDoctorId("DC101");
          drugRequestTO.setDrugName("crocin");
          drugRequestTO.setQuantity(4);
          drugRequestTO.setUrgent("UG");
          drugRequestTO.setReqStatus('W');
          new DoctorManager().requestMedicine(drugRequestTO);
    }



}
