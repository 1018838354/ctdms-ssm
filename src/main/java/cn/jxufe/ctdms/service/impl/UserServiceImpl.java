package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.bean.User;
import cn.jxufe.ctdms.bean.UserProfile;
import cn.jxufe.ctdms.dao.UserDao;
import cn.jxufe.ctdms.enums.UserProfileEnum;
import cn.jxufe.ctdms.service.UserService;
import cn.jxufe.ctdms.util.SnowflakeIdWorkerSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



@Service
public class UserServiceImpl implements UserService{

    @Resource(name="bCryptPasswordEncoder")
    PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserDao userDao;

    @Override
    public Long register(User user) {
        setUserInfo(user);
        Long id = SnowflakeIdWorkerSingleton.getInstance().nextId();
        user.setUId(id);
        userDao.save(user);
        return id;
    }

    private void setUserInfo(User user) {
        //分配权限  默认用户
        Set<UserProfile> userProfiles = new HashSet<>();
        userProfiles.add(new UserProfile(UserProfileEnum.TEACHER.getProfileTypeId(), UserProfileEnum.TEACHER.getUserProfileType()));
        userProfiles.add(new UserProfile(UserProfileEnum.USER.getProfileTypeId(), UserProfileEnum.USER.getUserProfileType()));
        user.setUserProfiles(userProfiles);
        setUserPassWord(user);
    }
    private void setUserPassWord(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
    }
    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public void registerTeachers(List<User> users) {
        for (User u : users){
            setUserInfo(u);
        }
        userDao.saves(users);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
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
