package cn.jxufe.ctdms.enums;

/**
 * 用户角色类型
 * 枚举
 * @author Moe
 *
 */
public enum UserProfileType {
	USER(1,"USER","用户"),
	TEACHER(10,"TEACHER","教师"),
	TEACHER_MASTER(40,"TEACHER_MASTER","教师负责人"),
	SECRETARY(80,"SECRETARY","教学秘书"),
	DIRECTOR(100,"DIRECTOR","系主任"),
	DEAN(300,"DEAN","教学院长"),
	ADMIN(999,"ADMIN","管理员");
	
	String userProfileType;
	String userProfileMsg;
	private int profileTypeId; 
	
	UserProfileType(int id,String msg,String type){
		this.profileTypeId = id;
		this.userProfileMsg = msg;
		this.userProfileType = type ;
	}
	private UserProfileType(String userProfileType){
		this.userProfileType = userProfileType;
	}
	
	public String getUserProfileType(){
		return userProfileType;
	}
	public int getProfileTypeId() {
		return profileTypeId;
	}

	public String getUserProfileMsg() {
		return userProfileMsg;
	}
}