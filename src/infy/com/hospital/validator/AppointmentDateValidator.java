package infy.com.hospital.validator;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class AppointmentDateValidator implements Validator{
                public void validate(FacesContext facesContext, UIComponent component, Object object)
                throws ValidatorException {

                                if(facesContext==null || component==null)
                                {
                                                return ;
                                }
                                if(!(component instanceof UIInput))
                                {
                                                return;
                                }
                                Date value=(Date)object;
                                Date d=new Date();
                                Calendar cal = new GregorianCalendar();
                                cal.setTime(d);
                                cal.add(Calendar.DAY_OF_MONTH, +30);
                                Date today30 = cal.getTime();
                                if(value.after(today30))
                                {
                                                FacesMessage message=new FacesMessage();
                                                message.setSummary("Pick the date before "+cal.get(5)+"-"+(cal.get(2)+1)+"-"+cal.get(1));
                                                message.setDetail("Pick the date before "+cal.get(5)+"-"+(cal.get(2)+1)+"-"+cal.get(1));
                                                throw new ValidatorException(message);
                                }
                                if(d.getMonth()==value.getMonth() && d.getDate()==value.getDate() && d.getYear()==value.getYear())
                                {}

                                else
                                {
                                if(value.before(d))
                                {
                                                FacesMessage message=new FacesMessage();
                                                message.setSummary("Date cannot be less than today's date");
                                                message.setDetail("Date cannot be less than today's date");
                                                throw new ValidatorException(message);
                                }
                                }
                }
}
