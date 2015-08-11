package infy.com.hospital.service;

import infy.com.hospital.entity.DrugRequestEntity;
import infy.com.hospital.entity.ProfileChangeRequestEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.ProfileChangeRequestTO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class ApprovalService {
	@SuppressWarnings("unchecked")
	public List<ProfileChangeRequestTO> getProRequests() throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		List<ProfileChangeRequestTO> pTOList = new ArrayList<ProfileChangeRequestTO>();
		try {
			em.getTransaction().begin();
			Query req = em.createQuery("select p from ProfileChangeRequestEntity p where p.requestStatus='W'");
			List<ProfileChangeRequestEntity> list = req.getResultList();
			for (int i = 0; i < list.size(); i++) {
				ProfileChangeRequestTO pTO = new ProfileChangeRequestTO();
				pTO.setAddress(list.get(i).getAddress());
				pTO.setConsultationFees(list.get(i).getConsultationFees());
				pTO.setDepartment(list.get(i).getDepartment());
				pTO.setDoctorId(list.get(i).getDoctorId());
				pTO.setEmployeeName(list.get(i).getEmployeeName());
				pTO.setPhoneNo(list.get(i).getPhoneNo());
				pTO.setQualification(list.get(i).getQualification());
				pTO.setReqNo(list.get(i).getReqNo());
				pTO.setRequestStatus(list.get(i).getRequestStatus());
				pTOList.add(pTO);
			}
			em.getTransaction().commit();
			return pTOList;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "getProRequests", e.getMessage());
			throw e;
		} finally{
			em.close();
			emf.close();
		}
	}

	public void updateAcceptProfile(List<ProfileChangeRequestTO> profileUpdateList) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		ProfileChangeRequestEntity pEnt = new ProfileChangeRequestEntity();
		try {
			em.getTransaction().begin();

			for (int i = 0; i < profileUpdateList.size(); i++) {
				pEnt.setAddress(profileUpdateList.get(i).getAddress());
				pEnt.setConsultationFees(profileUpdateList.get(i).getConsultationFees());
				pEnt.setDepartment(profileUpdateList.get(i).getDepartment());
				pEnt.setDoctorId(profileUpdateList.get(i).getDoctorId());
				pEnt.setEmployeeName(profileUpdateList.get(i).getEmployeeName());
				pEnt.setPhoneNo(profileUpdateList.get(i).getPhoneNo());
				pEnt.setQualification(profileUpdateList.get(i).getQualification());
				pEnt.setReqNo(profileUpdateList.get(i).getReqNo());
				pEnt.setRequestStatus('A');
				em.merge(pEnt);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateAcceptProfile", e.getMessage());
			throw e;
		} finally{
			em.close();
			emf.close();
		}
	}

	public void updateRejectProfile(List<ProfileChangeRequestTO> profileUpdateList) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("Post_project");
		EntityManager em = emf.createEntityManager();
		ProfileChangeRequestEntity pEnt = new ProfileChangeRequestEntity();
		try {
			em.getTransaction().begin();

			for (int i = 0; i < profileUpdateList.size(); i++) {
				pEnt.setAddress(profileUpdateList.get(i).getAddress());
				pEnt.setConsultationFees(profileUpdateList.get(i).getConsultationFees());
				pEnt.setDepartment(profileUpdateList.get(i).getDepartment());
				pEnt.setDoctorId(profileUpdateList.get(i).getDoctorId());
				pEnt.setEmployeeName(profileUpdateList.get(i).getEmployeeName());
				pEnt.setPhoneNo(profileUpdateList.get(i).getPhoneNo());
				pEnt.setQualification(profileUpdateList.get(i).getQualification());
				pEnt.setReqNo(profileUpdateList.get(i).getReqNo());
			}

			for (ProfileChangeRequestTO pcrTO : profileUpdateList) {
				pcrTO.setRequestStatus('R');
				pEnt.setRequestStatus(pcrTO.getRequestStatus());
				em.merge(pEnt);
			}
			em.getTransaction().commit();

		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(), "updateRejectProfile", e.getMessage());
			throw e;
		} finally{
			em.close();
			emf.close();
		}
	}
	
	
	
	public List<DrugRequestTO> getDrugRequests() throws Exception
	{

		EntityManagerFactory emf=null;
		EntityManager em=null;
		try {
		
			emf=Persistence.createEntityManagerFactory("Post_project");	
			em=emf.createEntityManager();			
			EntityTransaction et=em.getTransaction();
			et.begin();		
			Query query1=em.createQuery("select p from DrugRequestEntity p where p.reqStatus=?1");
			query1.setParameter(1,'W');
			List<?> l=query1.getResultList();			
			et.commit();
			List<DrugRequestTO> list=new ArrayList<DrugRequestTO>();
			for (int i=0;i<l.size();i++) 
			{
				DrugRequestTO dto=new DrugRequestTO();
				DrugRequestEntity de=(DrugRequestEntity) l.get(i);
				dto.setQuantity(de.getQuantity());
				dto.setReqStatus(de.getReqStatus());
				dto.setManufacturer(de.getCompany());
				dto.setDate(de.getRequestDate());
				dto.setDoctorId(de.getDoctorId());
				dto.setDrugName(de.getDrugName());
				dto.setDrugReqNo(de.getDrugReqNo());
				dto.setUrgent(de.getRequestType());					
				list.add(dto);				
			}
			return list;
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"getDrugRequests",e.getMessage());
			throw e;
		}
		finally{
			em.close();
		}
	}

	public void updateAcceptDrug(List<DrugRequestTO> drugUpdateList) throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Post_project");	
			em=emf.createEntityManager();			
			EntityTransaction et=em.getTransaction();		
			for(int i=0;i<drugUpdateList.size();i++)
			{
				DrugRequestEntity de=new DrugRequestEntity();
				DrugRequestTO dto=drugUpdateList.get(i);
				de.setCompany(dto.getManufacturer());
				de.setRequestDate(dto.getDate());
				de.setDoctorId(dto.getDoctorId());
				de.setDrugName(dto.getDrugName());
				de.setDrugReqNo(dto.getDrugReqNo());
				de.setQuantity(dto.getQuantity());
				de.setRequestType(dto.getUrgent());
				de.setReqStatus('A');
				et.begin();	
				em.merge(de);
				et.commit();
	
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"updateAcceptDrug",e.getMessage());
			throw e;
		}
		finally{
			em.close();
		}
	}
	public void updateRejectDrug(List<DrugRequestTO> drugUpdateList)throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		try {
			emf=Persistence.createEntityManagerFactory("Post_project");	
			em=emf.createEntityManager();			
			EntityTransaction et=em.getTransaction();
			for(int i=0;i<drugUpdateList.size();i++)
			{
				DrugRequestEntity de=new DrugRequestEntity();
				DrugRequestTO dto=drugUpdateList.get(i);
				de.setCompany(dto.getManufacturer());
				de.setRequestDate(dto.getDate());
				de.setDoctorId(dto.getDoctorId());
				de.setDrugName(dto.getDrugName());
				de.setDrugReqNo(dto.getDrugReqNo());
				de.setQuantity(dto.getQuantity());
				de.setRequestType(dto.getUrgent());
				de.setReqStatus('R');
				et.begin();	
				em.merge(de);
				et.commit();
			}
		} catch (Exception e) {
			ErrorLogger.logError(this.getClass().getName(),"updateAcceptDrug",e.getMessage());
			throw e;
		}
		finally{
			em.close();
		}
		
	}

}