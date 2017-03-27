package org.smartmenu.cronjobs;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import static org.quartz.JobBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.KeyMatcher.keyEquals;

public class JobScheduler 
{
	
	
	
	 public static void main(String[] args) throws SchedulerException 
	 {
		   SchedulerFactory schedFact = new StdSchedulerFactory();
	       Scheduler sched = schedFact.getScheduler();
	       sched.start();	       
	       scheduleDailyReportJob(sched);
	 }
	 
	 private static void scheduleDailyReportJob(Scheduler sched) throws SchedulerException
	 {
		 
		 JobDetail dailyReportJob = newJob(DailyReportJob.class)
	                .withIdentity("dailyReportJob","group1")
	                .build();

	        Trigger dailyReportJobTrigger = newTrigger()
	                .withIdentity("dailyReportJobTrigger", "group1")
	                .startNow()
	         //  .withSchedule(dailyAtHourAndMinute(5, 0).inTimeZone(TimeZone.getTimeZone("Asia/Riyadh")))
	                .build();
	        sched.scheduleJob(dailyReportJob,dailyReportJobTrigger);		 
	 }
	
	public JobScheduler()
	{
		   
	}
	
	

       

}
