package 系统显示.管理;

import java.util.Calendar;

public class Time {
	private String time;
	public Time(){
		String sm = null;
		String sd = null;
		String sh = null;
		String smi = null;
		String ss = null;
		String sms = null;
		Calendar c=Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int day = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		int msec = c.get(Calendar.MILLISECOND);
		if(month<10){
			sm ="0"+month;
		}else {
			sm = ""+month;
		}
		if(day<10){
			sd = "0"+day;
		}else {
			sd = ""+day;
		}
		if(hour<10){
			sh = "0"+hour;
		}else {
			sh = ""+hour;
		}
		if(min<10){
			smi = "0"+min;
		}else {
			smi = ""+min;
		}
		if(second<10){
			ss = "0"+second;
		}else {
			ss = ""+second;
		}
		if(msec<10){
			sms = "0"+msec;
		}else if (msec<100) {
			sms = "00"+msec;
		}else {
			sms = ""+msec;
		}
		time = year+sm+sd+sh+smi+ss+sms;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}
