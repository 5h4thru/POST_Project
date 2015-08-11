package infy.com.hospital.service;

import infy.com.hospital.entity.DoctorEntity;
import infy.com.hospital.entity.EmployeeEntity;
import infy.com.hospital.entity.LoginEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.EmployeeTO;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class EmployeeService {
	public String addReceptionist(EmployeeTO eto) throws Exception{

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		try {
			EmployeeEntity eEnt = new EmployeeEntity();
			LoginEntity lEnt = new LoginEntity();
			lEnt.setRole(eto.getDesignation());			

			eEnt.setEmployeeName(eto.getEmployeeName());
			eEnt.setQualification(eto.getQualification());
			eEnt.setDesignation(eto.getDesignation());
			eEnt.setAddress(eto.getAddress());
			eEnt.setPhoneNo(eto.getPhoneNo());
			eEnt.setDateOfJoining(eto.getDateOfJoining());

			em.getTransaction().begin();
			Query user = em.createQuery("select max(e.userName) from LoginEntity e where e.userName like 'RE%'");
			String temp = (String) user.getSingleResult();
			if(temp==null){
				lEnt.setPassword("RE@100");
				lEnt.setUserName("RE100");
				em.persist(lEnt);
			} else{
				String auto = temp.substring(2, temp.length());
				int number = Integer.parseInt(auto);
				number = number+1;
				lEnt.setPassword("RE"+"@"+number);
				lEnt.setUserName("RE"+number);
			}
			em.persist(lEnt);
			em.getTransaction().commit();

			try {
				eEnt.setUserName(lEnt.getUserName());
				em.getTransaction().begin();
				em.persist(eEnt);
				em.getTransaction().commit();
			} catch (Exception e) {
				ErrorLogger.logError(this.getClass().getName(), "addReceptionist", e.getMessage());
				throw e;
			} finally{
				em.close();
				emf.close();
			}
			String temp1 = eEnt.getUserName();
			return(temp1);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addReceptionist", e.getMessage());
			throw e;
		}
	}

	public void updateUserNameRE(String empNo)throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			EmployeeEntity eEnt = em.find(EmployeeEntity.class, empNo);
			eEnt.setUserName("RE"+eEnt.getUserName());
			em.merge(eEnt);
			em.getTransaction().commit();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateUserNameRE", e.getMessage());
			throw e;
		}
	}

	public String addDoctor(EmployeeTO eto) throws Exception{

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		try {
			EmployeeEntity eEnt = new EmployeeEntity();
			LoginEntity lEnt = new LoginEntity();
			DoctorEntity dEnt = new DoctorEntity();
			lEnt.setRole(eto.getDesignation());			

			eEnt.setEmployeeName(eto.getEmployeeName());
			eEnt.setQualification(eto.getQualification());
			eEnt.setDesignation(eto.getDesignation());
			eEnt.setAddress(eto.getAddress());
			eEnt.setPhoneNo(eto.getPhoneNo());
			eEnt.setDateOfJoining(eto.getDateOfJoining());

			em.getTransaction().begin();
			Query user = em.createQuery("select max(e.userName) from LoginEntity e where e.userName like 'DC%'");
			String temp = (String) user.getSingleResult();
			String auto = temp.substring(2, temp.length());
			int number = Integer.parseInt(auto);
			number = number+1;
			lEnt.setPassword("DC"+"@"+number);
			lEnt.setUserName("DC"+number);
			em.persist(lEnt);
			em.getTransaction().commit();


			try {
				eEnt.setUserName(lEnt.getUserName());
				dEnt.setUserName(lEnt.getUserName());
				dEnt.setConsultationFees(eto.getConsultationFees());
				dEnt.setDepartment(eto.getDepartment());
				dEnt.setSlot1(eto.getSlot1());
				dEnt.setSlot2(eto.getSlot2());
				dEnt.setSlot3(eto.getSlot3());
				em.getTransaction().begin();
				em.persist(eEnt);
				em.persist(dEnt);
				em.getTransaction().commit();
			} catch (Exception e) {
				ErrorLogger.logError(this.getClass().getName(), "addDoctor", e.getMessage());
				throw e;
			} finally{
				em.close();
				emf.close();
			}
			String temp1 = eEnt.getUserName();
			return(temp1);
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "addDoctor", e.getMessage());
			throw e;
		}
	}

	public void updateUserNameDC(String empNo) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			EmployeeEntity eEnt = em.find(EmployeeEntity.class, empNo);
			eEnt.setUserName("DC"+eEnt.getUserName());
			em.merge(eEnt);
			em.getTransaction().commit();
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateUserNameDC", e.getMessage());
			throw e;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SelectItem> getNames() throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<EmployeeEntity> temp = new ArrayList<EmployeeEntity>();
		try {
			Query q = em.createQuery("select e from EmployeeEntity e");
			temp = q.getResultList();
			for (int i=0; i<temp.size(); i++){
				list.add(new SelectItem(temp.get(i).getUserName(),temp.get(i).getUserName()));	
			}
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getNames", e.getMessage());
			throw e;
		}finally{
			em.close();
			emf.close();
		}
	}


	@SuppressWarnings("unchecked")
	public List<SelectItem> getDocs() throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		List<SelectItem> list = new ArrayList<SelectItem>();
		List<DoctorEntity> temp = new ArrayList<DoctorEntity>();
		try {
			Query q = em.createQuery("select d from DoctorEntity d");
			temp = q.getResultList();
			for (int i=0; i<temp.size(); i++){
				list.add(new SelectItem(temp.get(i).getUserName(),temp.get(i).getUserName()));	
			}
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getDocs", e.getMessage());
			throw e;
		}finally{
			em.close();
			emf.close();
		}
	}


	@SuppressWarnings("unchecked")
	public String delEmployee(String userName) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			DoctorEntity dEnt = em.find(DoctorEntity.class, userName);
			String temp = null;
			Query ipdCheck = em.createQuery("select i from IpdAppointmentEntity i where i.doctorId=?1");
			ipdCheck.setParameter(1, userName);
			List list1 = ipdCheck.getResultList();
			Query opdCheck = em.createQuery("select i from OpdAppointmentEntity i where i.doctorId=?1");
			opdCheck.setParameter(1, userName);
			List list2 = opdCheck.getResultList();
			if(dEnt!=null){
				if(list1.size()==0 &&  list2.size()==0){
					temp = dEnt.getUserName();
					em.remove(dEnt);

					Query q = em.createQuery("select e from EmployeeEntity e where e.userName=?1");
					q.setParameter(1,userName);
					EmployeeEntity eEnt = (EmployeeEntity) q.getSingleResult();
					em.remove(eEnt);

					Query q1 = em.createQuery("select e from LoginEntity e where e.userName=?1");
					q1.setParameter(1,userName);
					LoginEntity lEnt = (LoginEntity) q1.getSingleResult();
					em.remove(lEnt);

				}
			} else{
				Query q = em.createQuery("select e from EmployeeEntity e where e.userName=?1");
				q.setParameter(1,userName);
				EmployeeEntity eEnt = (EmployeeEntity) q.getSingleResult();
				em.remove(eEnt);

				Query q1 = em.createQuery("select e from LoginEntity e where e.userName=?1");
				q1.setParameter(1,userName);
				LoginEntity lEnt = (LoginEntity) q1.getSingleResult();
				em.remove(lEnt);
			}
			em.getTransaction().commit();
			return temp;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "delEmployee", e.getMessage());
			throw e;
		}
	}

	public List<EmployeeTO> getTO(String userName) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		DoctorEntity dEnt = new DoctorEntity();
		EmployeeEntity eEnt = new EmployeeEntity();
		EmployeeTO eto = new EmployeeTO();
		List<EmployeeTO> list = new ArrayList<EmployeeTO>();
		try {
			em.getTransaction().begin();
			dEnt = em.find(DoctorEntity.class, userName);
			Query eEntQ = em.createQuery("select e from EmployeeEntity e where e.userName=?1");
			eEntQ.setParameter(1, userName);
			eEnt = (EmployeeEntity) eEntQ.getSingleResult();
			eto.setAddress(eEnt.getAddress());
			eto.setConsultationFees(dEnt.getConsultationFees());
			eto.setDateOfJoining(eEnt.getDateOfJoining());
			eto.setDepartment(dEnt.getDepartment());
			eto.setDesignation(eEnt.getDesignation());
			eto.setEmployeeName(eEnt.getEmployeeName());
			eto.setEmployeeNo(eEnt.getEmployeeNo());
			eto.setPhoneNo(eEnt.getPhoneNo());
			eto.setQualification(eEnt.getQualification());
			eto.setSlot1(dEnt.getSlot1());
			eto.setSlot2(dEnt.getSlot2());
			eto.setSlot3(dEnt.getSlot3());
			eto.setUserName(eEnt.getUserName());
			em.getTransaction().commit();
			list.add(eto);
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getTO", e.getMessage());
			throw e;
		}
	}

	public void updateDoctor(EmployeeTO eto) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		DoctorEntity dEnt = new DoctorEntity();
		EmployeeEntity eEnt = new EmployeeEntity();
		try {
			eEnt.setEmployeeNo(eto.getEmployeeNo());
			eEnt.setEmployeeName(eto.getEmployeeName());
			eEnt.setUserName(eto.getUserName());
			eEnt.setQualification(eto.getQualification());
			eEnt.setPhoneNo(eto.getPhoneNo());
			eEnt.setDesignation(eto.getDesignation());
			eEnt.setDateOfJoining(eto.getDateOfJoining());
			eEnt.setAddress(eto.getAddress());
			dEnt.setDepartment(eto.getDepartment());
			dEnt.setConsultationFees(eto.getConsultationFees());
			dEnt.setSlot1(eto.getSlot1());
			dEnt.setSlot2(eto.getSlot2());
			dEnt.setSlot3(eto.getSlot3());
			dEnt.setUserName(eto.getUserName());
			em.getTransaction().begin();
			em.merge(dEnt);
			em.merge(eEnt);
			em.getTransaction().commit();

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateDoctor", e.getMessage());
			throw e;
		} finally{
			em.close();
			emf.close();
		}
	}


}