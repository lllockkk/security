package com.lllockkk.security.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lllockkk.security.dto.User;
import com.lllockkk.security.exception.UserNotExistException;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Security;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by L on 2017/8/21.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userDetails;
    }

    @GetMapping
    @JsonView(User.UserSimpleView.class)
    public List<User> query(@RequestParam String username) {
        System.out.println(username);
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    @GetMapping("/{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    @ApiOperation("查寻用户详情")
    public User getInfo(@ApiParam("用户名id") @PathVariable String id) {
        if ("1".equals(id))
            throw new UserNotExistException(id);

        User user = new User();
        user.setUsername("tom");
        user.setPassword("123");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
        }

        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        return user;
    }

    @PutMapping
    public User update(@Valid @RequestBody User user, BindingResult errors) {
        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()) );
        }

        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.MULTI_LINE_STYLE));
        user.setId("1");
        return user;
    }

    @DeleteMapping("/{id:\\d+}")
    public void delete(@PathVariable String id) {
        System.out.println(id);
    }
}
