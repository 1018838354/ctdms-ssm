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

    @GetMapping(value = "/login")
    public ModelAndView loginPage(
        @RequestParam(value = "error",required = false)String error){
        ModelAndView modelv = new ModelAndView();
        if(error != null)
            modelv.addObject("error","账号或密码错误");
        modelv.setViewName("login");
        return modelv;
    }
}
