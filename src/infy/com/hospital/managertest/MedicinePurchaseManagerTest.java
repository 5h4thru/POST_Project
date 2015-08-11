package infy.com.hospital.managertest;

import static org.junit.Assert.assertTrue;
import infy.com.hospital.exception.InsufficientQuantityException;
import infy.com.hospital.exception.InvalidPatientDetailsException;
import infy.com.hospital.manager.MedicinePurchaseManager;
import infy.com.hospital.to.DrugRequestTO;

import org.junit.Test;

public class MedicinePurchaseManagerTest {

	@Test
	public void testListOfDrugs() throws Exception 
	{
		MedicinePurchaseManager mpm=new MedicinePurchaseManager();
		assertTrue((mpm.listOfDrugs()!=null));
		//fail("Not yet implemented");
	}

	@Test
	public void testMakePayment() throws Exception  
	{
		DrugRequestTO drt=new DrugRequestTO();
		drt.setRegistrationNo("6001");
		drt.setQuantity(5);
		drt.setDrugName("crosin");
		drt.setPatientType("IPD");
		drt.setCostPerUnit(10);
		MedicinePurchaseManager mpm=new MedicinePurchaseManager();
		assertTrue(mpm.makePayment(drt)!=null);
		
	}

	@Test(expected=Exception.class)
	public void testInvalidDrugNameMakePayment() throws Exception  
	{
		DrugRequestTO drt=new DrugRequestTO();
		drt.setRegistrationNo("6001");
		drt.setQuantity(5);
		drt.setDrugName("crocin");
		drt.setPatientType("IPD");
		drt.setCostPerUnit(10);
		MedicinePurchaseManager mpm=new MedicinePurchaseManager();
		mpm.makePayment(drt);
		
	}

	@Test(expected=InsufficientQuantityException.class)
	public void testInsufficientQuantityMakePayment() throws InsufficientQuantityException,Exception  
	{
		DrugRequestTO drt=new DrugRequestTO();
		drt.setRegistrationNo("6001");
		drt.setQuantity(50000);
		drt.setDrugName("Rem");
		drt.setPatientType("IPD");
		drt.setCostPerUnit(10);
		MedicinePurchaseManager mpm=new MedicinePurchaseManager();
		mpm.makePayment(drt);
		
	}

	

	@Test
	public void testValidatePatientDetails() throws InvalidPatientDetailsException, Exception 
	{
		MedicinePurchaseManager mpm=new MedicinePurchaseManager();
		assertTrue(mpm.validatePatientDetails("IPD", "6001"));
		//fail("Not yet implemented");
	}
	@Test(expected=InvalidPatientDetailsException.class)
	public void testInvalidPatientDetails() throws InvalidPatientDetailsException, Exception 
	{
		MedicinePurchaseManager mpm=new MedicinePurchaseManager();
		mpm.validatePatientDetails("OPD", "6001");
		//fail("Not yet implemented");
	}

}
