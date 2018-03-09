package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.bean.User;
import cn.jxufe.ctdms.bean.UserProfile;
import cn.jxufe.ctdms.dao.UserDao;
import cn.jxufe.ctdms.enums.UserProfileType;
import cn.jxufe.ctdms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    @Bean
    BCryptPasswordEncoder getPasswordEncoder(){return new BCryptPasswordEncoder();}
    @Autowired
    UserDao userDao;
    @Override
    public Long register(User user) {
        setUserInfo(user);
        return userDao.save(user);
    }

    private void setUserInfo(User user) {
        //分配权限  默认用户
        Set<UserProfile> userProfiles = new HashSet<>();
        userProfiles.add(new UserProfile(UserProfileType.TEACHER.getProfileTypeId(),UserProfileType.TEACHER.getUserProfileType()));
        userProfiles.add(new UserProfile(UserProfileType.USER.getProfileTypeId(),UserProfileType.USER.getUserProfileType()));
        user.setUserProfiles(userProfiles);
        setUserPassWord(user);
    }
    private void setUserPassWord(User user){
        user.setPassword(getPasswordEncoder().encode(user.getPassword()));
    }
    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public void registerTearchers(List<User> users) {

    }

    @Override
    public void forbid(long uid, int forbid) {

    }

    @Override
    public void delete(long uid) {

    }

    @Override
    public void modifyPassword(long uid, String passWord) {

    }

    @Override
    public void modifyInfo(long uid, User user) {

    }

    @Override
    public void loginRecord(User user, String ip) {

    }
}
