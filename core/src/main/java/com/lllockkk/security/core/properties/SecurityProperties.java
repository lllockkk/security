package com.lllockkk.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by L on 2017/11/14.
 */
@ConfigurationProperties(prefix = "lllockkk.security")
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
