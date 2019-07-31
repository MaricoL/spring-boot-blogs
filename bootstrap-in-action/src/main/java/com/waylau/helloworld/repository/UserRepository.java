package com.waylau.helloworld.repository;

import com.waylau.helloworld.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
}
