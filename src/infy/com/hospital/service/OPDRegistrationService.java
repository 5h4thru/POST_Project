package infy.com.hospital.service;

import infy.com.hospital.entity.BillingEntity;
import infy.com.hospital.entity.DoctorEntity;
import infy.com.hospital.entity.EmployeeEntity;
import infy.com.hospital.entity.LoginEntity;
import infy.com.hospital.entity.OpdAppointmentEntity;
import infy.com.hospital.entity.PatientEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.BillingTO;
import infy.com.hospital.to.OpdAppointmentTO;
import infy.com.hospital.to.PatientTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class OPDRegistrationService {
	
	public String addPatient(PatientTO patientTO) throws Exception{
		Date sysDate=new Date();
		PatientEntity patientEntity=new PatientEntity();
		patientEntity.setFirstName(patientTO.getFirstName());
		patientEntity.setLastName(patientTO.getLastName());
		patientEntity.setAge(patientTO.getAge());
		patientEntity.setAddress(patientTO.getAddress());
		patientEntity.setContactPerson(patientTO.getContactPerson());
		patientEntity.setNationality(patientTO.getNationality());
		patientEntity.setPhoneNo(patientTO.getPhoneNo());
		EntityManager em=null;
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			et.begin();	
			em.persist(patientEntity);
			LoginEntity loginEntity=new LoginEntity();
			loginEntity.setUserName("PA"+patientEntity.getPatientId());
			loginEntity.setPassword("PA@"+patientEntity.getPatientId());
			loginEntity.setRole("PA");
			loginEntity.setLastLoginDate(sysDate);
			em.merge(loginEntity);		
			et.commit();
			return patientEntity.getPatientId();
		}
		catch (Exception e) {
//			System.out.println("am here");
//			e.printStackTrace();
			ErrorLogger.logError(this.getClass().getName(),"addPatient", e.getMessage());
			throw e;
		}
	}


	public void updateUserName(String patientId) throws Exception
	{
		EntityManager em=null;
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			et.begin();	
			PatientEntity patientEntity1=em.find(PatientEntity.class,patientId);
			patientEntity1.setUserName("PA"+patientId);
			em.merge(patientEntity1);
			et.commit();
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"updateUserName", e.getMessage());
			throw e;
		}
	}
	public List<SelectItem> getDoctors1() throws Exception
	{
		EntityManager em=null;
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			et.begin();	
		Query q=em.createQuery("select k from DoctorEntity k");
		List<DoctorEntity> doctorList=new ArrayList<DoctorEntity>();
			doctorList=q.getResultList();
		List<EmployeeEntity> emplList=new ArrayList<EmployeeEntity>();
		List<SelectItem> doctors=new ArrayList<SelectItem>();
		for(DoctorEntity d:doctorList)
		{
			Query q1=em.createQuery("select c from EmployeeEntity c where c.userName=?1");
			q1.setParameter(1,d.getUserName());
			emplList.add((EmployeeEntity) q1.getSingleResult());	
		}
		et.commit();
		for(EmployeeEntity e:emplList)
		{
			doctors.add(new SelectItem(e.getUserName(),e.getEmployeeName()));
		}
		return doctors;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getDoctors1", e.getMessage());
			throw e;
		}
		
	}
	public List<SelectItem> getSlots(String doctorName) throws Exception
	{
		EntityManager em=null;
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			List<SelectItem> slots=new ArrayList<SelectItem>();
			List<DoctorEntity> dList=new ArrayList<DoctorEntity>();
			et.begin();	
			Query q=em.createQuery("select d from DoctorEntity d where d.userName=?1");
			q.setParameter(1,doctorName);
			dList= q.getResultList();
			DoctorEntity d=dList.get(0);
			et.commit();
			if(d.getSlot1().equals("AV"))
			{
			slots.add(new SelectItem("S1","Slot1"));
			}
			if(d.getSlot2().equals("AV"))
			{
			slots.add(new SelectItem("S2","Slot2"));
			}
			if(d.getSlot3().equals("AV"))
			{
				slots.add(new SelectItem("S3","Slot3"));
			}
			return slots;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getSlots", e.getMessage());
			throw e;
		}
	}
	public String registerOPD(OpdAppointmentTO opdAppointmentTO) throws Exception
	{
		EntityManager em=null;
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			
			
		OpdAppointmentEntity appointmentEntity=new OpdAppointmentEntity();
		appointmentEntity.setDoctorId(opdAppointmentTO.getDoctorId());
		appointmentEntity.setPatientId(opdAppointmentTO.getPatientId());
		appointmentEntity.setSlot(opdAppointmentTO.getSlot());
		appointmentEntity.setDateOfAdmission(opdAppointmentTO.getDateOfAdmission());
		appointmentEntity.setReasonForAppointment(opdAppointmentTO.getReasonForAppointment());
		appointmentEntity.setStatus("C");
		
		Query q1=em.createQuery("select oa from OpdAppointmentEntity oa where oa.doctorId=?1 and oa.patientId=?2 and oa.dateOfAdmission=?3 and oa.slot=?4");
		q1.setParameter(1, opdAppointmentTO.getDoctorId());
		q1.setParameter(2, opdAppointmentTO.getPatientId());
		q1.setParameter(3, opdAppointmentTO.getDateOfAdmission());
		q1.setParameter(4, opdAppointmentTO.getSlot());
		List<OpdAppointmentEntity> li=q1.getResultList();
		
		if(li.size()==0)
		{
		Query query=em.createQuery("select oae from OpdAppointmentEntity oae where oae.doctorId=?1 and oae.dateOfAdmission=?2 and oae.slot=?3");
		query.setParameter(1, opdAppointmentTO.getDoctorId());
		query.setParameter(2, opdAppointmentTO.getDateOfAdmission());
		query.setParameter(3, opdAppointmentTO.getSlot());
		List<OpdAppointmentEntity> rs=query.getResultList();
		if(rs.size()>3)
		{
			return null;
		}
		else
		{
			et.begin();
			em.persist(appointmentEntity);
			et.commit();
			return appointmentEntity.getRegistrationNo();
		}
		}
		else
		{
			return "Registered";
		}
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"registerOPD",e.getMessage());
			throw e;
		}
	}
	public String addBillDetails(BillingTO billingTO) throws Exception
	{
		EntityManager em=null;
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			BillingEntity billingEntity=new BillingEntity();
			billingEntity.setAmount(billingTO.getAmount());
			billingEntity.setRegistrationNo(billingTO.getRegistrationNo());
			billingEntity.setBillingDate(billingTO.getBillingDate());
			billingEntity.setDescription("Doctor Consultation Fees");
			et.begin();
			em.persist(billingEntity);
			et.commit();
			return billingEntity.getBillNo();
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"addBillDetails",e.getMessage());
			throw e;
		}
	}
	public boolean checkPatientId(String userName)
	{
		EntityManager em=null;
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			et.begin();	
			Query q=em.createQuery("select k from PatientEntity k where k.userName=?1");
			q.setParameter(1,userName);
			PatientEntity patientEntity=(PatientEntity) q.getSingleResult();
			et.commit();
		if(patientEntity!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"ipdRegisterPatient", e.getMessage());
			return false;
		}
	}
}
