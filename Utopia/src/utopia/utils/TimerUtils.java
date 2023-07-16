package utopia.utils;

import javax.ejb.ScheduleExpression;
import javax.ejb.TimerConfig;

import utopia.enumeration.ConfigEnum;

public class TimerUtils {
	
	public TimerConfig getTimerConfig(String info) {
		
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo("info");
		return timerConfig;
		
	}
	
	public ScheduleExpression getScheduleExpression(String time) {
		
		String clndr[] = time.split(":");
		TimerConfig timerConfig = new TimerConfig();
		timerConfig.setInfo("CalendarProgTimerDemo_Info");
		ScheduleExpression schedule = new ScheduleExpression();
		schedule.hour(clndr[0]).minute(clndr[1]).second(clndr[2]);
		
		return schedule;
		
		
	}

}
