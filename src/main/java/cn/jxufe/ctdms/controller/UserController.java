package cn.jxufe.ctdms.controller;

import cn.jxufe.ctdms.bean.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    @GetMapping("/users")
    public List<User> query(){
        return null;
    }

}
