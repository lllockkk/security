package com.lllockkk.security.core.validate.code.sms;

/**
 * Created by L on 2017/8/21.
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
