package infy.com.hospital.service;


import infy.com.hospital.entity.EmployeeEntity;
import infy.com.hospital.entity.IpdAppointmentEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.IpdAppointmentTO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class InPatientEnquiryService {


	public IpdAppointmentTO inpatientEnquiry(String patientUsername)throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		List<IpdAppointmentEntity> l=new ArrayList<IpdAppointmentEntity>();
		try {
			IpdAppointmentTO iat=new IpdAppointmentTO();
			emf=Persistence.createEntityManagerFactory("Post_project");	
			em=emf.createEntityManager();
			IpdAppointmentEntity ipd=new IpdAppointmentEntity();
			EntityTransaction et=em.getTransaction();
		
			Query query1=em.createQuery("select p from IpdAppointmentEntity p where p.username=?1 and p.admissionStatus=?2");
	query1.setParameter(1,patientUsername);	
			query1.setParameter(2,"A");
		
		 l=query1.getResultList();
			Iterator i=l.iterator();
			if(l.size()!=0)
			{
			while(i.hasNext())
			{
				ipd=(IpdAppointmentEntity) i.next();
			}
			iat.setDepartment(ipd.getDepartment());
			iat.setBedNo(ipd.getBedNo());
			iat.setRegistrationNo(ipd.getRegistrationNo());
			iat.setDoctorId(ipd.getDoctorId());
			iat.setWardNo(ipd.getWardNo());
			iat.setDateOfAdmission(ipd.getDateOfAdmission());
			iat.setReasonForAdmission(ipd.getReasonForAdmission());			
			}
			else
			{
				return null;
			}
			EmployeeEntity ee=new EmployeeEntity();
			et.begin();
			Query query2=em.createQuery("select e from EmployeeEntity e where e.userName=?1");
			query2.setParameter(1,iat.getDoctorId());			
			List l1=query2.getResultList();
			et.commit();
			Iterator i1=l1.iterator();
			while(i1.hasNext())
			{
				ee=(EmployeeEntity) i1.next();
			}
			
			iat.setDoctorName(ee.getEmployeeName());			
			return iat;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"InpatientEnquiry",e.getMessage());
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
