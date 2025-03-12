package com.zwp.usercenter.once.importuser;

import com.zwp.usercenter.mapper.UserMapper;
import com.zwp.usercenter.model.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/**
 * 导入用户任务
 *
 * @author zwp
 */
@Component
public class InsertUsers {

    @Autowired
    private UserMapper userMapper;

    /**
     * 批量插入用户
     */
//    @Scheduled(initialDelay = 5000, fixedRate = Long.MAX_VALUE)
// 配置了一个 "延迟执行一次" 的定时任务，，它会在 Spring 容器启动 5 秒后执行 一次，fixedRate 设置为几乎无限大，Spring 容器不会再自动触发任务的后续执行
    public void doInsertUsers() {
        StopWatch stopWatch = new StopWatch(); // 用于计时
        System.out.println("goodgoodgood");
        stopWatch.start();
        final int INSERT_NUM = 1000;
        for (int i = 0; i < INSERT_NUM; i++) {
            User user = new User();
            user.setUsername("假zwp");
            user.setUserAccount("fakezwp");
            user.setAvatarUrl("https://636f-codenav-8grj8px727565176-1256524210.tcb.qcloud.la/img/logo.png");
            user.setGender(0);
            user.setUserPassword("12345678");
            user.setPhone("123");
            user.setEmail("123@qq.com");
            user.setTags("[]");
            user.setUserStatus(0);
            user.setUserRole(0);
            user.setPlanetCode("11111111");
            userMapper.insert(user);
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
