package com.lllockkk.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {
    ValidateCode generate(HttpServletRequest request);
}
