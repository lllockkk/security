package com.lllockkk.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;

public interface ValidateCodeGenerator {
    ImageCode generate(HttpServletRequest request);
}
