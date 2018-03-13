package cn.jxufe.ctdms.controller;

import cn.jxufe.ctdms.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 *  用户  controller
 *
 */
@Controller
public class UserController {
    @GetMapping("/users")
    public List<User> query(){
        return null;
    }


    @GetMapping("/{uId}/home")
    public String home(){
        return "home";
    }

}
