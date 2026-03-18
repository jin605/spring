package com.beyond.university.auth.model.service;

import com.beyond.university.auth.model.mapper.AuthMapper;
import com.beyond.university.auth.model.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 *  UserDetailsService
 *      - 전달 받은 username을 통해 데이터베이스에서 일치하는 사용자를 찾아 USerDetails 객체를 생성하고 반환한다.
 *
 *  UserDetails
 *      - 사용자의 정보와 권한 정보 등을 담고 있는 객체이다.
 *
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AuthMapper authMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = authMapper.selectUserByUserName(username);

        if (user == null) {

            throw new UsernameNotFoundException(String.format("%s not found",username));
        }

        log.info("Username : {}", user.getUsername());

        return user;

    }

}
