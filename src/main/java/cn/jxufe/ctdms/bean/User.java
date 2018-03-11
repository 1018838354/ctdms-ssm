package cn.jxufe.ctdms.bean;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class User implements UserDetails{

	private long uId;

	private String username;

	private String password;

	private String state = UserState.ACTIVE.getState();

	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	@Override
	public String toString() {
		return "User{" +
				"uId=" + uId +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", state='" + state + '\'' +
				'}';
	}

	public User() {
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> auths = new ArrayList<>();

		for (UserProfile role : userProfiles) {
			auths.add(new SimpleGrantedAuthority("ROLE_"+role.getProfile()));
		}
		return auths;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public enum UserState {

		ACTIVE("Active"),
		INACTIVE("Inactive"),
		DELETED("Deleted"),
		LOCKED("Locked");

		private String state;

		private UserState(final String state){
			this.state = state;
		}

		public String getState(){
			return this.state;
		}

		@Override
		public String toString(){
			return this.state;
		}

		public String getName(){
			return this.name();
		}


	}


	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}


	public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (obj instanceof String) {
			if (this.username.equals((String) obj)) {
				return true;
			}
			return false;
		}
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
