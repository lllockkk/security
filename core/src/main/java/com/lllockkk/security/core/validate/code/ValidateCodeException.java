package com.lllockkk.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by L on 2017/11/15.
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String msg) {
        super(msg);
    }
}
