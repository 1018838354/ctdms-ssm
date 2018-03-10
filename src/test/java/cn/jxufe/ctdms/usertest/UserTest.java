package cn.jxufe.ctdms.usertest;


import cn.jxufe.ctdms.bean.User;
import cn.jxufe.ctdms.dao.UserDao;
import cn.jxufe.ctdms.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
用户测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {
    private static final Logger log = LoggerFactory.getLogger(UserTest.class);

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc ;

    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //模拟登录测试
    @Test
    public void login_test() throws Exception {
        String json = mockMvc.perform(post("/login")
        .param("username","张驰")
        .param("password","123").contentType(MediaType.APPLICATION_JSON_UTF8)
        ).andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.print(json);
    }

    //findByUsername Dao 测试
    @Test
    public void selectUserTest(){
        User user = userDao.findByUsername("张驰");

        Assert.isTrue(user.getUsername().equals("张驰"),"查找张驰 错误");
    }

    //register findByUid Dao 测试
    @Test
    public void registerUserTest(){
        User user = new User();
        user.setUsername("fsaf");
        user.setPassword("123");
        try {
            Long userId = userService.register(user);
            System.out.println(userId);
            User user2 = userDao.findByUId(userId);
            Assert.isTrue(user.getUsername().equals(user2.getUsername()),"test_user");

        }catch (Exception e){
            //用户已存在
            e.printStackTrace();
        }
     }
}
