package cn.jxufe.ctdms.bean;


import cn.jxufe.ctdms.enums.UserProfileEnum;

/**
 * table user_profile
 * 用户 角色 映射表
 * @author Moe
 *
 */

public class UserProfile {

	private int id;	

	private String type = UserProfileEnum.USER.getUserProfileType();
	
	public UserProfile(){
		
	}
	public UserProfile(int id ,String type){
		this.id=id;
		this.type=type;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof UserProfile))
			return false;
		UserProfile other = (UserProfile) obj;
		if (id != other.id)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ",  type=" + type	+ "]";
	}
	

}