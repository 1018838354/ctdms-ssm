package cn.jxufe.ctdms.controller;

import cn.jxufe.ctdms.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.websocket.server.PathParam;
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
    public String home(@PathParam("uId")Integer uId){
        return "home";
    }

    @GetMapping("/{uId}/mycourses")
    public String courses(@PathParam("uId")Integer uId){
        return "mycourses";
    }
    @GetMapping("/{uId}/upcourse")
    public String upcourse(@PathParam("uId")Integer uId){
        return "upcourse";
    }

}
