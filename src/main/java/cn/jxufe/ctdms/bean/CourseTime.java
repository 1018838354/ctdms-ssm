package cn.jxufe.ctdms.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 教师上课时间,课程表
 * @author Moe
 *
 */
public class CourseTime {
	long uId;			//用户id

	long ctId;			//课程时间id

	long cId;			//课程id

	int lastTime ;  	//几节课

	int day ;  			//星期几

	int classIndex; 	//上,中,下午的 第几节课

	String classRoom;	//教室


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
	}
	
	@Override
	public String toString() { 
		return "星期"+day+"-"+classIndex+"-"+lastTime+"节课"+"-"+classRoom;
	}

	public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
	}

	public long getCtId() {
		return ctId;
	}

	public void setCtId(long ctId) {
		this.ctId = ctId;
	}

	public int getLastTime() {
		return lastTime;
	}

	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getClassIndex() {
		return classIndex;
	}

	public void setClassIndex(int classIndex) {
		this.classIndex = classIndex;
	}

	public long getcId() {
		return cId;
	}

	public void setcId(long cId) {
		this.cId = cId;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}
}

