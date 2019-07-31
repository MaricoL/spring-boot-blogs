package com.waylau.helloworld.repository;


import com.waylau.helloworld.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository2 {

    private AtomicLong atomicLong = new AtomicLong();
    private ConcurrentHashMap<Long, User> userMap = new ConcurrentHashMap<>();

    public User saveOrUpdate(User user) {
        Long id = user.getId();
        if (id == null) {
            // 新建
            id = atomicLong.incrementAndGet();
            user.setId(id);
        }
        userMap.put(id, user);
        return user;
    }

    public void deleteUser(Long id) {
        userMap.remove(id);
    }

    public User findById(Long id) {
        return userMap.get(id);
    }

    public List<User> listUser() {
        return new ArrayList<>(userMap.values());
    }

}
