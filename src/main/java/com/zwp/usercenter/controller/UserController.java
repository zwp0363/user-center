package com.zwp.usercenter.controller;

import com.zwp.usercenter.model.domain.User;
import com.zwp.usercenter.model.domain.request.UserLoginRequest;
import com.zwp.usercenter.model.domain.request.UserRegisterRequest;
import com.zwp.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口
 *
 * @author zwp
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    // @RequestBody 是 Spring MVC 中用于处理 HTTP 请求体的关键注解。它负责将请求体数据转换为 Java 对象，方便 Controller 方法使用
    public Long userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            return null;
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            return null;
        }
        return userService.userRegister(userAccount, userPassword, checkPassword);
    }

    @PostMapping("/login")
    public User userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }
        return userService.userLogin(userAccount, userPassword, request);
    }
}
