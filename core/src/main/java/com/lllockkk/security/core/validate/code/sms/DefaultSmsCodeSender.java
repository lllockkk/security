package com.lllockkk.security.core.validate.code.sms;

/**
 * Created by L on 2017/8/21.
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机 " + mobile + " 发送验证码" + code);
    }
}
