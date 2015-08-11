package infy.com.hospital.service;

import infy.com.hospital.entity.BillingEntity;
import infy.com.hospital.entity.DoctorEntity;
import infy.com.hospital.entity.IpdAppointmentEntity;
import infy.com.hospital.entity.WardEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.BillingTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BillingService {
	
	public BillingTO payIPD(String registrationNo,String patientId,Double amount) throws Exception
	{
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			BillingEntity billingEntity=new BillingEntity();
			billingEntity.setAmount(amount);
			billingEntity.setBillingDate(new Date());
			billingEntity.setDescription("Advance");
			billingEntity.setRegistrationNo(registrationNo);
			em.persist(billingEntity);
			em.getTransaction().commit();
			
			BillingTO billingTO=new BillingTO();
			billingTO.setAmount(billingEntity.getAmount());
			billingTO.setBillingDate(billingEntity.getBillingDate());
			billingTO.setBillNo(billingEntity.getBillNo());
			billingTO.setDescription(billingEntity.getDescription());
			billingTO.setRegistrationNo(billingEntity.getRegistrationNo());
			
			return billingTO;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "payIPD",e.getMessage());
			throw e;
		}
	}
	public Double discharge(String registrationNo,String patientID) throws Exception{
		Double amount=null;
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			IpdAppointmentEntity ipdAppointmentEntity=new IpdAppointmentEntity();
			ipdAppointmentEntity=em.find(IpdAppointmentEntity.class, registrationNo);
			
			Query query=em.createQuery("select SUM(be.amount) from BillingEntity be where be.registrationNo=?1");
			query.setParameter(1, registrationNo);
			List<?> list=query.getResultList();
			double total=0.0;
			if(list.get(0)!=null){
			total=(Double)list.get(0);
			}
			Query query1=em.createQuery("select sum(be.amount) from BillingEntity be where be.description=?1 and be.registrationNo=?2");
			query1.setParameter(1, "Advance");
			query1.setParameter(2, registrationNo);
			List<?> list1=query1.getResultList();
			double advance=0.0;
			if(list.get(0)!=null){
			advance=(Double)list1.get(0);
			}
			double expense=total-advance;
			
			Date d=new Date();
			long stay=d.getTime()-ipdAppointmentEntity.getDateOfAdmission().getTime();
            stay=stay/(24*60*60*1000);

			if(stay==0){
				stay=1;
			}
			WardEntity wardEntity=em.find(WardEntity.class, ipdAppointmentEntity.getWardNo());
			double wardCost=stay*wardEntity.getWPrice();
			
			DoctorEntity doctorEntity=em.find(DoctorEntity.class, ipdAppointmentEntity.getDoctorId());
			double consultationFee=doctorEntity.getConsultationFees();
			
			
			amount=advance-expense-wardCost-consultationFee;
			
//			if(amount>=0){
//				ipdAppointmentEntity.setDateOfDischarge(d);
//               	ipdAppointmentEntity.setAdmissionStatus("D");
//                em.getTransaction().begin();
//                em.merge(ipdAppointmentEntity);
//                em.getTransaction().commit();
//
//		}
		
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(),"discharge",e.getMessage());
			throw e;
		}
		return amount;
	}
	public List<BillingTO> generateBill(String registrationNo,String patientId) throws Exception{
		List<BillingTO> billList=new ArrayList<BillingTO>();
		try
		{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			EntityManager em=emf.createEntityManager();
			em.getTransaction().begin();
			
			Query query=em.createQuery("select be from BillingEntity be where be.registrationNo=?1");
			query.setParameter(1, registrationNo);
			List<?> billingList=query.getResultList();
			for(int i=0;i<billingList.size();i++)
			{
				BillingEntity billingEntity=(BillingEntity)billingList.get(i);
				BillingTO billingTO=new BillingTO();
				billingTO.setAmount(billingEntity.getAmount());
				billingTO.setBillingDate(billingEntity.getBillingDate());
				billingTO.setBillNo(billingEntity.getBillNo());
				billingTO.setDescription(billingEntity.getDescription());
				billingTO.setRegistrationNo(billingEntity.getRegistrationNo());
				billList.add(billingTO);
			}
			return billList;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "GenerateBillService",e.getMessage());
			throw e;
		}
		
	}
	public int validations(String registrationNo,String patientId)throws Exception{
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			EntityManager em=emf.createEntityManager();
		IpdAppointmentEntity ipdAppointmentEntity=em.find(IpdAppointmentEntity.class, registrationNo);
		if(ipdAppointmentEntity==null)
		{
			return -1;
		}
		if(!(ipdAppointmentEntity.getUsername().equals(patientId))){
			return -3;
		}
		if(ipdAppointmentEntity.getAdmissionStatus().equalsIgnoreCase("D"))
		{
			return -2;
		}
		return 1;
		}
		catch(Exception e)
		{
			ErrorLogger.logError(e.getClass().getName(), "GenerateBillService",e.getMessage());
			throw e;
		}
	}

}
