package cn.jxufe.ctdms.enums;

/**
 * 用户角色 枚举
 * @author Moe
 *
 */
public enum UserProfileEnum {
	USER(0,"USER","用户"),
	TEACHER(1,"TEACHER","教师"),
	TEACHER_MASTER(11,"TEACHER_MASTER","教师负责人"),
	SECRETARY(111,"SECRETARY","教学秘书"),
	DIRECTOR(111,"DIRECTOR","系主任"),
	DEAN(1111,"DEAN","教学院长"),
	ADMIN(9999,"ADMIN","管理员");

	String userProfileType;
	String userProfileMsg;
	private int profileTypeId;

	UserProfileEnum(int id, String msg, String type){
		this.profileTypeId = id;
		this.userProfileMsg = msg;
		this.userProfileType = type ;
	}
	private UserProfileEnum(String userProfileType){
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