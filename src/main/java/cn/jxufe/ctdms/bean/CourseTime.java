package cn.jxufe.ctdms.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 上课时间
 * @author Moe
 *
 */
public class CourseTime {
	private long id;

	public static final int MORNING = 0,AFTERNOON = 1,NIGHT = 2 ,MORING_MAX_LENGTH = 4;
	public static void main(String[] args) { 
		System.out.println(new CourseTime("2-123"));
		System.out.println(new CourseTime("2-567"));
		System.out.println(new CourseTime("4-89A"));
		System.out.println(new CourseTime("5-A"));
	} 
	public CourseTime(){
		
	}
	public CourseTime(String classTime){ 
		day = Integer.parseInt(classTime.substring(0, 1)) ;
		classTime = classTime.substring(2,classTime.length());
		lastTime = classTime.length();
		if(!classTime.startsWith("A")){ 
			classIndex = Integer.parseInt(classTime.substring(0, 1));
		}else{
			classIndex = 10;
		}
		
		if(classTime.endsWith("A")){ 
			this.classTime = NIGHT ;
		}else{
			if(classIndex > MORING_MAX_LENGTH ){
				this.classTime = AFTERNOON;
			}else{
				this.classTime = MORNING;
			}
		}
			
	}
	private int lastTime ;  //几节课
	private int day ;  //星期几

	private int classTime;  //上,中,下 午
	private int classIndex; //上,中,下午的 第几节课
	private String classRoom;
	
	@Override
	public String toString() { 
		return "星期"+day+"-时候:"+classTime+"-"+classIndex+"-"+lastTime+"节课"+"-"+classRoom;
	}
	@JsonIgnore
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
 
	public int getLastTime() {
		return lastTime;
	}

	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}

	public String getClassRoom() {
		return classRoom;
	}
	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getClassTime() {
		return classTime;
	}

	public void setClassTime(int classTime) {
		this.classTime = classTime;
	}

	public int getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}
 
	
}

