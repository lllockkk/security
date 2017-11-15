package com.lllockkk.security.core.properties;

/**
 * Created by L on 2017/11/14.
 */
public class BrowserProperties {
    private String loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;

    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }
}
