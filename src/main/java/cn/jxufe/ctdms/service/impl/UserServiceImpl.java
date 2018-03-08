package cn.jxufe.ctdms.service.impl;

import cn.jxufe.ctdms.bean.User;
import cn.jxufe.ctdms.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Override
    public User register(User user) {
        return null;
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
