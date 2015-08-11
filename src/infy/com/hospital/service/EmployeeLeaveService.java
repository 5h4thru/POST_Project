package infy.com.hospital.service;

import infy.com.hospital.entity.EmployeeLeaveEntity;
import infy.com.hospital.entity.OpdAppointmentEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.EmployeeLeaveTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class EmployeeLeaveService {


	public String leaveApplication(EmployeeLeaveTO eto) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		EmployeeLeaveEntity elEnt = new EmployeeLeaveEntity();
		try {
			elEnt.setUserName(eto.getUserName());
			elEnt.setToDate(eto.getToDate());
			elEnt.setFromDate(eto.getFromDate());
			elEnt.setPhoneNo(eto.getPhoneNo());
			elEnt.setLeaveType(eto.getLeaveType());
			
			em.getTransaction().begin();
			em.persist(elEnt);
			em.getTransaction().commit();
			
			String leaveId = elEnt.getLeaveId();
			return leaveId;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "leaveApplication", e.getMessage());
			throw e;
		}
	}


	@SuppressWarnings("unchecked")
	public boolean checkSchedule(Date toDate, Date fromDate, String userName) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		List<OpdAppointmentEntity> opd = new ArrayList<OpdAppointmentEntity>();
		try {
			
			em.getTransaction().begin();
			Query check = em.createQuery("select opd from OpdAppointmentEntity opd where opd.doctorId = ?1 and (opd.dateOfAdmission between ?2 and ?3)");
			check.setParameter(1, userName);
			check.setParameter(2, fromDate);
			check.setParameter(3, toDate);
			opd = (List<OpdAppointmentEntity>) check.getResultList();
			
			em.getTransaction().commit();
			
			
			if(opd.size()==0){
				return true;
			}
			else {
				return false;
			}
			
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "checkSchedule", e.getMessage());
			throw e;
		}
	}
}
