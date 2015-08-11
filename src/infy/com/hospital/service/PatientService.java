package infy.com.hospital.service;

import infy.com.hospital.entity.DoctorEntity;
import infy.com.hospital.entity.OpdAppointmentEntity;
import infy.com.hospital.entity.PatientEntity;
import infy.com.hospital.entity.PaymentEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.DoctorTO;
import infy.com.hospital.to.OpdAppointmentTO;
import infy.com.hospital.to.PatientTO;
import infy.com.hospital.to.PaymentTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class PatientService {	
	public DoctorTO checkAppointment(OpdAppointmentTO oTO) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			String doctorId = oTO.getDoctorId();
			
			DoctorEntity doctor = em.find(DoctorEntity.class, doctorId);
			DoctorTO dTO = null;
			if(doctor != null) {
				dTO = new DoctorTO();
				dTO.setUserName(doctor.getUserName());
				dTO.setConsultationFees(doctor.getConsultationFees());
				dTO.setDepartment(doctor.getDepartment());
				dTO.setSlot1(doctor.getSlot1());
				dTO.setSlot2(doctor.getSlot2());
				dTO.setSlot3(doctor.getSlot3());
			}
			return dTO;
		}

		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "checkAppointment", e.getMessage());
			throw e;
		}

		finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	public int noOfAppointments(OpdAppointmentTO oTO) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			
			Query query = em.createQuery("select count(op) from OpdAppointmentEntity op where op.dateOfAdmission=?1 and op.patientId=?2");
			query.setParameter(1, oTO.getDateOfAdmission());
			query.setParameter(2, oTO.getPatientId());
			
			int count = ((Long) query.getSingleResult()).intValue();
			return count;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "noOfAppointments", e.getMessage());
			throw e;
		}

		finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	public String makePayment(PaymentTO pt, OpdAppointmentTO oTO) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		EntityTransaction et = null;
		try {
			emf = Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			et = em.getTransaction();
			
			PaymentEntity payment = new PaymentEntity();
			payment.setPatientId(pt.getPatientId());
			payment.setDoctorId(pt.getDoctorId());
			payment.setAmount(pt.getAmount());
			payment.setDateOfPayment(pt.getDateOfPayment());
			
			OpdAppointmentEntity opdAppointmentEntity = new OpdAppointmentEntity();
			opdAppointmentEntity.setDoctorId(oTO.getDoctorId());
			opdAppointmentEntity.setPatientId(oTO.getPatientId());
			opdAppointmentEntity.setReasonForAppointment(oTO.getReasonForAppointment());
			opdAppointmentEntity.setDateOfAdmission(oTO.getDateOfAdmission());
			opdAppointmentEntity.setSlot(oTO.getSlot());
			opdAppointmentEntity.setStatus("A");
			
			et.begin();
			em.persist(payment);
			em.persist(opdAppointmentEntity);
			et.commit();
			
			String payNo = payment.getPayNo();
			return payNo;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "makePayment", e.getMessage());
			throw e;
		}
		
		finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<OpdAppointmentTO> viewAppointmentMade(String patientId) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			
			Query opdQuery = em.createQuery("select opd from OpdAppointmentEntity opd where opd.patientId=?1 and opd.status='A'");
			opdQuery.setParameter(1, patientId);
			
			List<OpdAppointmentEntity> opdList = (List<OpdAppointmentEntity>) opdQuery.getResultList();
			List<OpdAppointmentTO> opdTOList = new ArrayList<OpdAppointmentTO>();
			
			for (OpdAppointmentEntity opdAppointmentEntity : opdList) {
				OpdAppointmentTO opdTO = new OpdAppointmentTO();
				
				opdTO.setRegistrationNo(opdAppointmentEntity.getRegistrationNo());
				opdTO.setDoctorId(opdAppointmentEntity.getDoctorId());
				opdTO.setPatientId(opdAppointmentEntity.getPatientId());
				opdTO.setReasonForAppointment(opdAppointmentEntity.getReasonForAppointment());
				opdTO.setDateOfAdmission(opdAppointmentEntity.getDateOfAdmission());
				opdTO.setSlot(opdAppointmentEntity.getSlot());
				opdTO.setStatus(opdAppointmentEntity.getStatus());
				
				opdTOList.add(opdTO);
			}
			
			return opdTOList;
		}
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "viewAppointmentMade", e.getMessage());
			throw e;
		}
		
		finally {
			if(em != null) {
				em.close();
			}
		}
	}
	
	public void cancelAppointment(String regNo) throws Exception {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			EntityTransaction et = em.getTransaction();
			
			et.begin();
			OpdAppointmentEntity opd = em.find(OpdAppointmentEntity.class, regNo);
			opd.setStatus("C");
			em.merge(opd);
			et.commit();
		}		
		
		catch(Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "cancelAppointment", e.getMessage());
			throw e;
		}
		
		finally {
			if(em != null) {
				em.close();
			}
		}
	}
	

	public List<PaymentTO> viewPayments(String patientId) throws Exception
	{
		
		EntityManagerFactory emf = null;
		EntityManager em = null;
		
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			
			Query query = em.createQuery("select pe from PaymentEntity pe where pe.patientId=?1");
			
			query.setParameter(1, patientId);
			List<PaymentEntity> entityList=query.getResultList();
			List<PaymentTO> toList=new ArrayList<PaymentTO>();
			
			
			for(PaymentEntity paymentEntity:entityList)
			{
				PaymentTO paymentTO=new PaymentTO();
			paymentTO.setDoctorId(paymentEntity.getDoctorId());
			paymentTO.setPatientId(paymentEntity.getPatientId());
			paymentTO.setAmount(paymentEntity.getAmount());
			paymentTO.setDateOfPayment(paymentEntity.getDateOfPayment());
			paymentTO.setPayNo(paymentEntity.getPayNo());
			toList.add(paymentTO);	
			}
			
			return toList;
			
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "viewPayments", e.getMessage());
			
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
	}
	public PatientTO findPatient(String patientId)throws Exception
	{
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try
		{
			emf=Persistence.createEntityManagerFactory("Post_project");
			em = emf.createEntityManager();
			Query query = em.createQuery("select pe from PatientEntity pe where pe.userName=?1");
			query.setParameter(1, patientId);
			List<PatientEntity> entityList=query.getResultList();
			
			PatientTO patientTO=new PatientTO();
			for(PatientEntity patientEntity:entityList)
			{
				patientTO.setFirstName(patientEntity.getFirstName());
				patientTO.setLastName(patientEntity.getLastName());
				patientTO.setContactPerson(patientEntity.getContactPerson());
				patientTO.setAddress(patientEntity.getAddress());
				patientTO.setPhoneNo(patientEntity.getPhoneNo());
			}
			return patientTO;
		}
		catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "findPatient", e.getMessage());
			
			throw e;
		}
		finally
		{
			if(em!=null)
			{
				em.close();
			}
		}
	}
}
