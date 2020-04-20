package edu.bjtu.javaee.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import edu.bjtu.javaee.domain.User;
import edu.bjtu.javaee.controller.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
}
