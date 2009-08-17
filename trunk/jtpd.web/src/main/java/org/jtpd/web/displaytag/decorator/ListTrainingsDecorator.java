package org.jtpd.web.displaytag.decorator;

import org.displaytag.decorator.TableDecorator;
import org.jtpd.domain.model.Activities;
import org.jtpd.util.DateUtils;

public class ListTrainingsDecorator extends TableDecorator {

	
	public String getLocation() {
		
		int location = Integer.parseInt(  ((Activities)(this.getCurrentRowObject())).getLocation() );
		String cityName = null ;
		if (location == 34) {
			cityName  = "\u0130stanbul" ;
		}
		return cityName;
	}
	
	public String getActivityName() {
		String activityName = ((Activities)(this.getCurrentRowObject())).getTrainingNames().getActivityName();
		String infoPage = ((Activities)(this.getCurrentRowObject())).getInfoPage() ;
		if (infoPage == null) {
			infoPage = "#";
		}
		String link = "<a href=\""+infoPage+ "\">"+activityName+"</a>" ;
		return link;
	}
	
	
	
	public String getStartDate() {
		try {
			String startDate = (( Activities)(this.getCurrentRowObject() )).getStartDate() ;
			return DateUtils.convertToFormattedDate(startDate);
		} catch (NumberFormatException e) {
			return null ;
		}
		
	}
	
	public String getEndDate() {
		try {
			String endDate = (( Activities)(this.getCurrentRowObject() )).getEndDate() ;
			return DateUtils.convertToFormattedDate(endDate);
		} catch (NumberFormatException e) {
			return null ;
		}
	}
	
	public String getRegister() {
		
		int id =  ((Activities)(this.getCurrentRowObject())).getId() ;		
		String link = "<a href=\"TrainingRegistration.jsf?activityId="+id + "\">Kay\u0131t i\u00e7in t\u0131klay\u0131n\u0131z</a>&nbsp&nbsp" ;		
		return link;
	}
}
