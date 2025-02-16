package com.zwp.usercenter;

import com.zwp.usercenter.mapper.UserMapper;
import com.zwp.usercenter.model.User;
import org.junit.Assert;
//import org.junit.jupiter.api.Test; // 如果引入的是这个JUnit 5的Test（即JUnit 5 会自动识别测试类），就不用加@Runwith或指定启动类
import org.junit.Test; // JUnit4需要加@Runwith或指定启动类
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = UserCenterApplication.class) // 第一种方法，想要实现如下相关功能，指定启动类
// @RunWith(SpringRunner.class) // 第二种方法
// 想要在 JUnit 4 测试中利用 Spring 的强大功能 (例如依赖注入、Spring 上下文等) 时
// 就需要使用 @RunWith(SpringRunner.class) 注解来让 Spring TestContext Framework 接管测试的执行
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5,userList.size()); //判断前后是否一致
        userList.forEach(System.out::println);
    }

}