package cn.jxufe.ctdms.bean;


import cn.jxufe.ctdms.enums.UserProfileEnum;

/**
 * 用户 角色 映射表
 * @author Moe
 *
 */
public class UserProfile {

	private int id;	

	private String profile = UserProfileEnum.USER.getUserProfileType();
	
	public UserProfile(){
		
	}
	public UserProfile(int id ,String profile){
		this.id=id;
		this.profile=profile;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((profile == null) ? 0 : profile.hashCode());
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
		if (profile == null) {
			if (other.profile != null)
				return false;
		} else if (!profile.equals(other.profile))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserProfile [id=" + id + ",  profile=" + profile	+ "]";
	}
	

}