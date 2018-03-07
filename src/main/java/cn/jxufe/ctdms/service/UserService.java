package cn.jxufe.ctdms.service;

import cn.jxufe.ctdms.bean.User;

import java.util.List;

public interface UserService {
	/*
		注册用户
	*/
	User register(User user);

	/*
		根据用户名查找用户
	 */
	User findByUserName(String userName);

	void registerTearchers(List<User> users);

	//List<CourseTeacherTime> getCTT(long userId);

	void forbid(long uid, int forbid);

	void delete(long uid);

	void modifyPassword(long uid, String passWord);

	void modifyInfo(long uid, User user);
	
	void loginRecord(User user, String ip);
}