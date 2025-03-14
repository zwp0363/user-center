package com.zwp.usercenter.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedissonTest {

    @Autowired
    private RedissonClient redissonClient;

    @Test
    void test() {
        // list,数据存在本地JVM内存中
        ArrayList<Object> list = new ArrayList<>();
        list.add("zwp");
        System.out.println("list" + list.get(0));
//        list.remove(0);

        // 数据存在redis内存中
        RList<Object> rList = redissonClient.getList("test-list");
        rList.add("zwp");
        System.out.println("rList" + rList.get(0));
//        rList.remove(0);

        // map
        HashMap<Object, Integer> map = new HashMap<>();
        map.put("zwp", 1);
        map.get("zwp");

        RMap<Object, Object> map1 = redissonClient.getMap("test-map");
        map1.put("zwp", 1);
    }

    //set

    //stack

    @Test
    void testWatchDog() {
        RLock lock = redissonClient.getLock("yupao:precachejob:docache:lock");
        try {
            // 只有一个线程能获取到锁
            if (lock.tryLock(0, -1, TimeUnit.MILLISECONDS)) {
                Thread.sleep(300000);
                System.out.println("getLock: " + Thread.currentThread().getId());
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            // 只能释放自己的锁
            if (lock.isHeldByCurrentThread()) {
                System.out.println("unLock: " + Thread.currentThread().getId());
                lock.unlock();
            }
        }
    }
}
