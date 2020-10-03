package domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Adjudicator {
	
	public static boolean checkExamTime(ExamTimeRange etr) {
		try {
			
			//Set up time stamp format
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			
			//put exam start and end time in two Date variable
			Date start = sdf.parse(etr.getStart_time());
			Date end = sdf.parse(etr.getEnd_time());

			//Get curent time
			Date now = new Date();
			
			//Convert String time into long
			
			long now_time = now.getTime();
			long start_time = start.getTime();
			long end_time = end.getTime();
			
			
			if(now_time >= start_time && now_time <= end_time) {

				return true;
				
			}
			
			
			
		}catch(Exception e) {
			
		}
		
		return false;

	}
}
