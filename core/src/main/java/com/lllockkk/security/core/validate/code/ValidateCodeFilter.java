package com.lllockkk.security.core.validate.code;

import com.lllockkk.security.core.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by L on 2017/8/21.
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        boolean action = false;
        for (String url : urls) {
            if (antPathMatcher.match(url, request.getRequestURI())) {
                action = true;
                break;
            }
        }

        if (action /*&& StringUtils.equalsIgnoreCase("post", request.getMethod())*/) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        String[] configUrl = StringUtils.split(securityProperties.getCode().getImage().getUrl(), ",");
        urls.addAll(Arrays.asList(configUrl));

        urls.add("/authentication/form");
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode imageCode = (ImageCode) sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);

        String code = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        if (StringUtils.isBlank(code))
            throw new ValidateCodeException("验证码不能为空");

        if (imageCode == null)
            throw new ValidateCodeException("验证码不存在");

        if (imageCode.isExpired())
            throw new ValidateCodeException("验证码已过期");

        if (!StringUtils.equals(imageCode.getCode(), code))
            throw new ValidateCodeException("验证码不匹配");

        sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
    }

    public AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return authenticationFailureHandler;
    }

    public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    public SessionStrategy getSessionStrategy() {
        return sessionStrategy;
    }

    public void setSessionStrategy(SessionStrategy sessionStrategy) {
        this.sessionStrategy = sessionStrategy;
    }

    public Set<String> getUrls() {
        return urls;
    }

    public void setUrls(Set<String> urls) {
        this.urls = urls;
    }

    public AntPathMatcher getAntPathMatcher() {
        return antPathMatcher;
    }

    public void setAntPathMatcher(AntPathMatcher antPathMatcher) {
        this.antPathMatcher = antPathMatcher;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
