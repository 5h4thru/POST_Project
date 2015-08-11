package infy.com.hospital.managertest;

import static org.junit.Assert.assertTrue;
import infy.com.hospital.manager.BioMedicalManager;
import infy.com.hospital.to.BiomedicalEquipmentTO;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class BioMedicalManagerTest {
	BioMedicalManager manager=null;
	@Before
	public void setUp() throws Exception {
		this.manager=new BioMedicalManager();
	}

	@After
	public void tearDown() throws Exception {
	}
	@Ignore
	@Test
	public void testAddEquipmentValidData() throws Exception{
		BiomedicalEquipmentTO equipto=new BiomedicalEquipmentTO();
		this.manager=new BioMedicalManager();
		equipto.setEquipmentName("Scissors");
		equipto.setCost(40);
		equipto.setQuantity(300);
		Date dateOfPurchase=new Date("23-Nov-2012");
		equipto.setDateOfPurchase(dateOfPurchase);
		assertTrue("Not a Valid Data",manager.addEquipment(equipto)!=null);
		
	}

	@Test
	public void testGetIdsValidData() throws Exception {
		assertTrue("List is not populated",manager.getIds()!=null);
	}
	@Ignore
	@Test
	public void testDelEquipmentValidData() throws Exception {
		assertTrue("DrugData Not Valid", manager.delEquipment("3240")!=null);
	}

	@Test
	public void testGetTOValidData() throws Exception{
		assertTrue("List is Not Populated",manager.getTO("2002")!=null);
	}
	
	@Test
	public void testUpdateEqpValidData() throws Exception{
		BiomedicalEquipmentTO equipto=new BiomedicalEquipmentTO();
		this.manager=new BioMedicalManager();
		equipto.setEquipmentNo("3286");
		equipto.setEquipmentName("Scissors");
		equipto.setCost(40);
		equipto.setQuantity(3000);
		Date dateOfPurchase=new Date("23-Nov-2012");
		equipto.setDateOfPurchase(dateOfPurchase);
		assertTrue("Not a Valid Data",manager.updateEqp(equipto)!=null);
	}

}
