package infy.com.hospital.managertest;

import static org.junit.Assert.assertTrue;
import infy.com.hospital.exception.InvalidPatientIdException;
import infy.com.hospital.manager.InPatientEnquiryManager;
import infy.com.hospital.to.IpdAppointmentTO;

import org.junit.Test;

public class InPatientEnquiryManagerTest {

	@Test
	public void testInpatientEnquiry() throws Exception {
	
		InPatientEnquiryManager im=new InPatientEnquiryManager();
		IpdAppointmentTO iat=new IpdAppointmentTO();
		iat=im.inpatientEnquiry("PA101");
		assertTrue("false",iat!=null);
	}
	
	@Test(expected=InvalidPatientIdException.class)
	public void failtestInpatientEnquiry() throws InvalidPatientIdException,Exception
	{
	InPatientEnquiryManager im=new InPatientEnquiryManager();
		im.inpatientEnquiry("PA11");		
	}

}
