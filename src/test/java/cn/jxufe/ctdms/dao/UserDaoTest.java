package cn.jxufe.ctdms.dao;

import cn.jxufe.ctdms.bean.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserDaoTest {
    List<User> users ;
    @Before
    public void init() {
        users = new ArrayList<>();
        for(int i=0;i<3;i++)
        {
            User user = new User();
            user.setUsername(i+"t");
            user.setPassword("123");
            users.add(user);
        }
    }
    @Autowired
    UserDao userDao;
    @Test
    public void test_insert_users(){
        System.out.println(users);
        userDao.saves(users);
    }

    @Autowired
    TestDao testDao;
    @Test
    public void test_insert(){
        cn.jxufe.ctdms.bean.Test t = new cn.jxufe.ctdms.bean.Test("hehe");
        long id = testDao.save(t);
        System.out.println(id+ " "+t.getcId());
    }
}
