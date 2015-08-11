package infy.com.hospital.service;

import infy.com.hospital.entity.OpdAppointmentEntity;
import infy.com.hospital.logger.ErrorLogger;
import infy.com.hospital.to.OpdAppointmentTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class AppointmentService {
	public List<OpdAppointmentTO> viewAppointment(String userName,Date date) throws Exception
	{
		List<OpdAppointmentTO> opList=new ArrayList<OpdAppointmentTO>();
		List<OpdAppointmentEntity> opList1=new ArrayList<OpdAppointmentEntity>();
		EntityManager em=null;
		try{
			EntityManagerFactory emf=Persistence.createEntityManagerFactory("Post_project");
			em=emf.createEntityManager();
			EntityTransaction et=em.getTransaction();
			et.begin();	
			Query q=em.createQuery("select k from OpdAppointmentEntity k where k.doctorId=?1 and k.dateOfAdmission=?2");
			q.setParameter(1,userName);
			q.setParameter(2,date);
			opList1=q.getResultList();
			et.commit();
			if(opList1.isEmpty()==false){
			for(OpdAppointmentEntity o:opList1)
			{
				OpdAppointmentTO op=new OpdAppointmentTO();
				op.setDateOfAdmission(o.getDateOfAdmission());
				op.setDoctorId(o.getDoctorId());
				op.setPatientId(o.getPatientId());
				op.setReasonForAppointment(o.getReasonForAppointment());
				op.setRegistrationNo(o.getRegistrationNo());
				op.setSlot(o.getSlot());
				op.setStatus(o.getStatus());
				opList.add(op);
			}
			}
			return opList;
		}catch (Exception e) {
//			System.out.println("am here");
//			e.printStackTrace();
			ErrorLogger.logError(this.getClass().getName(),"ipdRegisterPatient", e.getMessage());
			throw e;
		}
		}
}
