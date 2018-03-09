package cn.jxufe.ctdms.controller;

import cn.jxufe.ctdms.bean.User;
import cn.jxufe.ctdms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class UserController {
    @GetMapping("/users")
    public List<User> query(){
        return null;
    }

    @Autowired
    UserService userService;

    @PostMapping("/users")
    public void registerUser(@ModelAttribute("user") User user , HttpServletRequest request){
        userService.register(user);
    }

}
