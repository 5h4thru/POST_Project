package infy.com.hospital.service;

import java.util.Date;

import infy.com.hospital.entity.IpdAppointmentEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.DischargeTO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public class DischargeSummaryService {
public DischargeTO dischargeSummary(String registrationNo) throws Exception
{
	EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
	EntityManager em=emf.createEntityManager();
	try
	{
		EntityTransaction et=em.getTransaction();
		et.begin();
		IpdAppointmentEntity ipd=em.find(IpdAppointmentEntity.class,registrationNo );
		et.commit();
		
			
			ipd.setDateOfDischarge(new Date());
			DischargeTO dto=new DischargeTO();
			dto.setAdmissionStatus("D");
			dto.setBedNo(ipd.getBedNo());
			dto.setDateOfDischarge(ipd.getDateOfDischarge());
			dto.setDateOfAdmission(ipd.getDateOfAdmission());
			dto.setDepartment(ipd.getDepartment());
			dto.setDoctorId(ipd.getDoctorId());
			
			dto.setUsername(ipd.getUsername());
			dto.setRegistrationNo(ipd.getRegistrationNo());
			dto.setWardNo(ipd.getWardNo());
			dto.setReasonForAdmission(ipd.getReasonForAdmission());
			
			ipd.setAdmissionStatus("D");
			et.begin();
			em.merge(ipd);
			et.commit();
			return dto;
		
	}
	catch(Exception e)
	{
		ErrorLogger.logError(e.getClass().getName(), "DischargeSummary", e.getMessage());
		throw e;
	}
}
}
