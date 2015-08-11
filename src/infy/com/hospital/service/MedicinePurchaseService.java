package infy.com.hospital.service;



import infy.com.hospital.entity.BillingEntity;
import infy.com.hospital.entity.DrugEntity;
import infy.com.hospital.entity.IpdAppointmentEntity;
import infy.com.hospital.entity.OpdAppointmentEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.DrugRequestTO;
import infy.com.hospital.to.DrugTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class MedicinePurchaseService 

{

	public List<DrugTO> listOfDrugs() throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		EntityTransaction et=null;
		List<DrugTO> list=null;
		try
		{
		emf=Persistence.createEntityManagerFactory("Post_project");
		em=emf.createEntityManager();
		et=em.getTransaction();
		et.begin();
		Query q=em.createQuery("select d from DrugEntity d");
		List<DrugEntity> l=q.getResultList();
		list=new ArrayList<DrugTO>();
		
		for (DrugEntity de : l) 
		{
			
			DrugTO dto=new DrugTO();
			dto.setCompany(de.getCompany());
			dto.setCostPerUnit(de.getCostPerUnit());
			dto.setDateOfExpiry(de.getDateOfExpiry());
			dto.setDrugName(de.getDrugName());
			dto.setDrugNo(de.getDrugNo());
			dto.setQuantity(de.getQuantity());
			list.add(dto);
		}
		return list;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"listOfDrugs",e.getMessage());
			throw e;
		}
		finally
		{
			if(et!=null)
			{
				em.close();
			}
		}
		
	}
	
	public String makePayment(DrugRequestTO drt) throws Exception
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		EntityTransaction et=null;
		List<DrugTO> list=null;
		try
		{
		emf=Persistence.createEntityManagerFactory("Post_project");
		em=emf.createEntityManager();
		et=em.getTransaction();
		et.begin();
		Query q=em.createQuery("select d from DrugEntity d where d.drugName=?1");
		q.setParameter(1, drt.getDrugName());
		List l=q.getResultList();
		
		
		DrugEntity de=(DrugEntity) l.get(0);
		Query q1=em.createQuery("select b from BillingEntity b where b.registrationNo=?1");
		q1.setParameter(1, drt.getRegistrationNo());
		List<BillingEntity> l1=q1.getResultList();
		BillingEntity be=null;
		
		if(de.getQuantity()>drt.getQuantity())
		{
			//be=new BillingEntity();
			if(!l1.isEmpty())
			{
				be=new BillingEntity();
			be=l1.get(0);
			be.setAmount(be.getAmount()+drt.getQuantity()*de.getCostPerUnit());
			be.setBillingDate(new Date());
			//be.setRegistrationNo(drt.getRegistrationNo());
			be.setDescription("Medicine Purchase");
			em.merge(be);
			de.setQuantity(de.getQuantity()-drt.getQuantity());
			em.merge(de);
			et.commit();
			return be.getBillNo();
			}
			else
			{
				be=new BillingEntity();
				be.setAmount((double)drt.getQuantity()*de.getCostPerUnit());
				be.setBillingDate(new Date());
				be.setRegistrationNo(drt.getRegistrationNo());
				be.setDescription("Medicine Purchase");
				em.persist(be);
				de.setQuantity(de.getQuantity()-drt.getQuantity());
				em.merge(de);
				et.commit();
				return be.getBillNo();
			}
			
		}
		else
		{
			return null;
		}
		
		}
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"makePayment",e.getMessage());
			throw e;
		}
		finally
		{
			if(et!=null)
			{
				em.close();
			}
		}
	}
	
//	public int medicinePrice(String medicineName) throws Exception
//	{
//		EntityManagerFactory emf=null;
//		EntityManager em=null;
//		EntityTransaction et=null;
//		List<DrugTO> list=null;
//		try
//		{
//		emf=Persistence.createEntityManagerFactory("POST");
//		em=emf.createEntityManager();
//		et=em.getTransaction();
//		et.begin();
//		Query q=em.createQuery("select d.costPerUnit from DrugEntity d where d.drugName=?1");
//		q.setParameter(1, medicineName);
//		List l=q.getResultList();
//		int price=(Integer)l.get(0);
//		return price;
//		}
//		catch(Exception e)
//		{
//			ErrorLogger.logError(this.getClass().getName(),"medicinePrice",e.getMessage());
//			throw e;
//		}
//		finally
//		{
//			if(et!=null)
//			{
//				em.close();
//			}
//	}
//	}	
//	public boolean checkMedicineAvailability(DrugRequestTO drt)
//	{
//		return true;
//	}

	public Boolean validatePatientDetails(String patientType,
			String registrationNo) throws Exception 
	{
		EntityManagerFactory emf=null;
		EntityManager em=null;
		EntityTransaction et=null;
		List<DrugTO> list=null;
		try
		{
		emf=Persistence.createEntityManagerFactory("Post_project");
		em=emf.createEntityManager();
		et=em.getTransaction();
		et.begin();
		if(patientType.equals("IPD"))
		{
		IpdAppointmentEntity ipd=em.find(IpdAppointmentEntity.class, registrationNo);
		if(ipd!=null)
		{
			return true;
		}
		else
		{
			return false;
		}
		}
		else
		{
			OpdAppointmentEntity opd=em.find(OpdAppointmentEntity.class, registrationNo);
			if(opd!=null)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		}
		
		catch(Exception e)
		{
			ErrorLogger.logError(this.getClass().getName(),"validatePatientDetails",e.getMessage());
			throw e;
		}
		finally
		{
			if(et!=null)
			{
				em.close();
			}
	
		}
	}
}
