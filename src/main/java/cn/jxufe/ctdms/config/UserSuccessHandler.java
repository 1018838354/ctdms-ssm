package cn.jxufe.ctdms.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.jxufe.ctdms.bean.User;
import cn.jxufe.ctdms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * 用户登录成功 处理
 */

@Component
public class UserSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	@Autowired
	UserService userService;

	private User getUser() {
		String userName;
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			//userName = ((UserDetails) principal).getUsername();
			return (User)principal;
		} else {
			userName = principal.toString();
			return userService.findByUserName(userName);
		}
	}

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	protected void handle(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException {
		String targetUrl = determineTargetUrl(authentication); 
		User user = getUser();
		if(user != null) {
			targetUrl = "/" + user.getUId() + targetUrl;
			request.getSession().setAttribute("uId", user.getUId());
			System.out.println("登录:" + user.getUsername());
		}else{
			targetUrl = "/Access-Denied";
		}
		/*TODO 登录  记录 异步
		userService.loginRecord(user, getIpAddr(request));
		*/
		if (response.isCommitted()) {
			System.out.println("Can't redirect");
			return;
		}

		redirectStrategy.sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(Authentication authentication) {
		String url = "/home";
		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();

		List<String> roles = new ArrayList<String>();

		for (GrantedAuthority a : authorities) {
			roles.add(a.getAuthority());
		}
/*
		if (isUser(roles) || isAdmin(roles)) {
			url = "/";
		} else {
			url = "/Access-Denied";
		}
*/
		return url;
	}
/*
	private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // 多次反向代理后会有多个IP值，第一个为真实IP。
       if (!StringUtils.isEmptyOrWhitespaceOnly(ip) && !"unknown".equalsIgnoreCase(ip)) {
            if(ip.indexOf(",") > 0){
                return ip.substring(0,ip.indexOf(","));
            }else {
                return ip;
            }
        }
        return ip;
    }
    */

	public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

	private boolean isUser(List<String> roles) {
		if (roles.contains("ROLE_USER")) {
			return true;
		}
		return false;
	}

	private boolean isAdmin(List<String> roles) {
		if (roles.contains("ROLE_ADMIN")) {
			return true;
		}
		return false;
	}

}