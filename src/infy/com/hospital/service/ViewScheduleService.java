package infy.com.hospital.service;

import infy.com.hospital.entity.DoctorEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.DoctorTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ViewScheduleService {
	@SuppressWarnings("unchecked")
	public List<SelectItem> getNames() throws Exception{
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<String> temp = new ArrayList<String>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		try {
			Query names = em.createQuery("select distinct e.employeeName from EmployeeEntity e, DoctorEntity d where d.userName=e.userName");
			temp = names.getResultList();
			
			for (int i = 0; i < temp.size(); i++) {
				String name = temp.get(i);
				list.add(new SelectItem(name));	
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getSpecialization", e.getMessage());
			throw e;
		} finally{
			em.close();
			emf.close();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> getSpecialization() throws Exception{
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<String> temp = new ArrayList<String>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		try {
			Query special = em.createQuery("select distinct d.department from EmployeeEntity e, DoctorEntity d where d.userName=e.userName");
			temp = special.getResultList();
			
			for (int i = 0; i < temp.size(); i++) {
				String name = temp.get(i);
				list.add(new SelectItem(name));
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getSpecialization", e.getMessage());
			throw e;
		} finally{
			em.close();
			emf.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<DoctorTO> getDoctorSchedule(String name) throws Exception{
		List<DoctorTO> list = new ArrayList<DoctorTO>();
		List<DoctorEntity> dEnt = new ArrayList<DoctorEntity>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		try {
			Query getSchedule = em.createQuery("select d from DoctorEntity d, EmployeeEntity e where d.userName=e.userName and e.employeeName=?1");
			getSchedule.setParameter(1, name);
			dEnt = getSchedule.getResultList();
			for (int i = 0; i < dEnt.size(); i++) {
				DoctorTO dto = new DoctorTO();
				dto.setConsultationFees(dEnt.get(i).getConsultationFees());
				dto.setDepartment(dEnt.get(i).getDepartment());
				dto.setUserName(dEnt.get(i).getUserName());
				dto.setSlot1(dEnt.get(i).getSlot1());
				dto.setSlot2(dEnt.get(i).getSlot2());
				dto.setSlot3(dEnt.get(i).getSlot3());
				list.add(dto);
			}
			
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDoctorSchedule", e.getMessage());
			throw e;
		} finally{
			em.close();
			emf.close();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<DoctorTO> getSpecialSchedule(String name) throws Exception{
		List<DoctorTO> list = new ArrayList<DoctorTO>();
		List<DoctorEntity> dEnt = new ArrayList<DoctorEntity>();
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		try {
			Query getSchedule = em.createQuery("select d from DoctorEntity d where d.department=?1");
			getSchedule.setParameter(1, name);
			dEnt = getSchedule.getResultList();
			for (int i = 0; i < dEnt.size(); i++) {
				DoctorTO dto = new DoctorTO();
				dto.setConsultationFees(dEnt.get(i).getConsultationFees());
				dto.setDepartment(dEnt.get(i).getDepartment());
				dto.setUserName(dEnt.get(i).getUserName());
				dto.setSlot1(dEnt.get(i).getSlot1());
				dto.setSlot2(dEnt.get(i).getSlot2());
				dto.setSlot3(dEnt.get(i).getSlot3());
				list.add(dto);
			}			
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDoctorSchedule", e.getMessage());
			throw e;
		} finally{
			em.close();
			emf.close();
		}
		return list;
	}
}