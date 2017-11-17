package com.lllockkk.security.core.validate.code;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by L on 2017/8/21.
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ValidateCode generate(HttpServletRequest request) {
        return null;
    }
}
