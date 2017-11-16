package com.lllockkk.security.core.validate.code;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(HttpServletRequest request) {
        System.out.println("自定义生成验证码");
        return null;
    }
}
