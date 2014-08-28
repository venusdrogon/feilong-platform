/*import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Trigger;

public class SimpleJobDemo1 implements Job{

	private Calendar	calendar	= new GregorianCalendar();

	public void execute(JobExecutionContext context) throws JobExecutionException{
		calendar.setTime(new Date());
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hh = calendar.get(Calendar.HOUR_OF_DAY);
		int mi = calendar.get(Calendar.MINUTE);
		int ss = calendar.get(Calendar.SECOND);
		String s = year + "-" + month + "-" + day + " " + hh + ":" + mi + ":" + ss;
		Trigger trigger = context.getTrigger();
		JobDetail jobDetail = context.getJobDetail();
		JobDataMap jobDataMap = trigger.getJobDataMap();
		System.out.println(s + ",triggerName:" + trigger.getKey().getName() + ",triggerGroup:" + trigger.getKey().getName() + ",jobName:"
				+ jobDetail.getKey().getName() + ",jobGroup:" + jobDetail.getKey().getGroup() + ",jobMap:" + jobDataMap.getWrappedMap());
	}
}
*/