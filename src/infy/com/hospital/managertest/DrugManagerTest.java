package infy.com.hospital.managertest;

import static org.junit.Assert.assertTrue;
import infy.com.hospital.manager.DrugManager;
import infy.com.hospital.to.DrugTO;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DrugManagerTest {
	DrugManager drugmanager;
	@Before
	public void setUp() throws Exception {
		this.drugmanager=new DrugManager();
	}

	@After
	public void tearDown() throws Exception {
	}
	@Ignore
	@Test
	public void testAddDrugValidData() throws Exception{
		DrugTO drugto=new DrugTO();
		this.drugmanager=new DrugManager();
		
		drugto.setCompany("Acrin");
		drugto.setDrugName("Paracet");
		drugto.setCostPerUnit(30);
		drugto.setQuantity(400);
		Date date=new Date("22-Nov-2040");
		drugto.setDateOfExpiry(date);
	
		//String drugNo=drugmanager.addDrug(drugto);
		
		assertTrue("DrugData Not Valid", drugmanager.addDrug(drugto)!=null);
	}

	@Test
	public void testDelDrugValidData() throws Exception{
		this.drugmanager=new DrugManager();
		assertTrue("DrugData Not Valid", drugmanager.delDrug("3001")!=null);
	
	}

	@Test
	public void testGetIdsValidData() throws Exception{
		this.drugmanager=new DrugManager();
		assertTrue("List is Not Populated",drugmanager.getIds()!=null);
		
	}

	@Test
	public void testGetTOValidData() throws Exception{
		this.drugmanager=new DrugManager();
		assertTrue("List is Not Populated",drugmanager.getTO("3001")!=null);
	}

	@Test
	public void testUpdateDrug() throws Exception{
		DrugTO drugto=new DrugTO();
		this.drugmanager=new DrugManager();
		
		drugto.setCompany("Acrin");
		drugto.setDrugName("Paracet");
		drugto.setCostPerUnit(30);
		drugto.setQuantity(400);
		Date date=new Date("22-Nov-2040");
		drugto.setDateOfExpiry(date);
	
		//String drugNo=drugmanager.addDrug(drugto);
		
		assertTrue("DrugData Not Valid",drugmanager.updateDrug(drugto)!=null);
	}

}
