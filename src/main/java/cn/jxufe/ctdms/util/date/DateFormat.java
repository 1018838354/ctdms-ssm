package cn.jxufe.ctdms.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//格式化日期
public class DateFormat {
	
	public static String getFormatDate(){
		return timeMillisToString(System.currentTimeMillis());
	}
	private static SimpleDateFormat dateformat;
	private static SimpleDateFormat getSimpleDateForma(){
		if(dateformat == null)
			dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateformat;
	}
	/**
	 * (毫秒)转为日期
	 * @param millis
	 * @return
	 */
	public static String timeMillisToString(long millis){
		SimpleDateFormat dateformat = getSimpleDateForma();
		return  dateformat.format(millis);
	}
	/**
	 * 日期转为(秒)
	 * @param str
	 * @return
	 */  
	public static long timeStringToMillisSec(String str){
		SimpleDateFormat dateformat = getSimpleDateForma();
		try {
		    long time = dateformat.parse(str).getTime(); 
			return time;
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return 0;
	}
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
		System.out.println(timeStringToMillisSec("2017-08-24 00:00:00"));
	}
}
