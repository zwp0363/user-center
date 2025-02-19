package com.zwp.usercenter.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zwp.usercenter.model.domain.User;
import com.zwp.usercenter.model.domain.request.UserLoginRequest;
import com.zwp.usercenter.model.domain.request.UserRegisterRequest;
import com.zwp.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.zwp.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.zwp.usercenter.constant.UserConstant.USER_LOGIN_STATE;

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

    @GetMapping("/search")
    public List<User> searchUsers(String username, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return new ArrayList<>();
        }
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            userQueryWrapper.like("username", username);
        }
        List<User> userList = userService.list(userQueryWrapper);
        return userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        /* stream() 方法将 userList 转换为一个 Stream (流)。Stream 是 Java 8 引入的用于处理集合数据的抽象概念，它允许进行函数式风格的操作。
         map() 是 Stream 的一个 中间操作 (intermediate operation)。它会将流中的每个元素 转换 成另一个元素。
         user -> { ... } 是一个 Lambda 表达式，它定义了转换的逻辑。对于流中的每个 user 对象，这段 Lambda 表达式会被执行
         collect() 是 Stream 的一个 终端操作 (terminal operation)。它会 收集 流中的元素，并将它们 汇总成一个结果。
         Collectors.toList() 是 Collectors 类提供的一个静态方法，它会创建一个 Collector，用于将流中的元素收集到一个 新的 List 中。*/
    }

    @PostMapping("/delete")
    public boolean deleteUsers(@RequestBody long id, HttpServletRequest request) {
        if (!isAdmin(request)) {
            return false;
        }
        if (id <= 0) {
            return false;
        }
        return userService.removeById(id); // mybatis-plus的逻辑删除，更新为已删除状态
    }

    /**
     * 是否为管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可删除
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return  user != null && user.getUserRole() == ADMIN_ROLE;
    }
}
