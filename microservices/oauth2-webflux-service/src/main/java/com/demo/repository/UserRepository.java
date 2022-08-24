package com.demo.repository;

import com.demo.vo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findById (String username);
}