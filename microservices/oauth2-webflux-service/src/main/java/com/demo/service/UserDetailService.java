package com.demo.service;

import reactor.core.publisher.Mono;
import com.demo.repository.UserRepository;
import com.demo.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserDetails> loadUserByUsername(String username) throws UsernameNotFoundException {
        Mono<User> userInfo = userRepository.findById(username);
        return userInfo;
    }

}