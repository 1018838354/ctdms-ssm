package cn.jxufe.ctdms.enums;

/**
 * 用户角色 枚举
 * @author Moe
 *
 */
public enum UserProfileType {
	USER(1,"USER"),//普通用户
	TEACHER(10,"TEACHER"),//教师
	TEACHER_MASTER(40,"TEACHER_MASTER"),//教师负责人
	SECRETARY(80,"SECRETARY"),	//秘书
	DIRECTOR(100,"DIRECTOR"),	//系主任
	DEAN(300,"DEAN"),			//教学院长
	ADMIN(999,"ADMIN");
	
	String userProfileType;

	private int profileTypeId; 
	
	UserProfileType(int id,String msg){
		this.profileTypeId = id;
		this.userProfileType = msg ;
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
	
}