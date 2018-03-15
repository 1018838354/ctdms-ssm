package cn.jxufe.ctdms.config;

import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SimpleCsrfSecurityRequestMatcher implements RequestMatcher {
    private Pattern allowedMethods = Pattern
            .compile("^(GET|HEAD|TRACE|OPTIONS)$");

    @Override
    public boolean matches(HttpServletRequest request) {
        if (execludeUrls.size() > 0) {
            String servletPath = request.getServletPath();
            for (String url : execludeUrls) {
                if (servletPath.contains(url)) {
                    return false;
                }
            }
        }
        return !allowedMethods.matcher(request.getMethod()).matches();
    }
    /**
     * 需要排除的url列表
     */
    private final List<String> execludeUrls =new ArrayList<String>() {{
        add("/*/upload");
        add("/upload");
    }};
}
