package com.uu.spring.user;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.uu.spring.exception.ErrorCode;
import com.uu.spring.exception.ValidationException;
import com.uu.spring.organization.Organization;
import com.uu.spring.organization.OrganizationService;
import com.uu.spring.security.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    OrganizationService organizationService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User createUser(RegisterRequest registerRequest) {
        Optional<User> oldUser = userRepository.findUserByEmailOrUsername(registerRequest.getEmail(), registerRequest.getUsername());
        if (oldUser.isPresent()) {
            if (oldUser.get().getEmail().equals(registerRequest.getEmail()))
                throw ValidationException.of(ErrorCode.EMAIL_REGISTERED, "Email already registered");
            throw ValidationException.of(ErrorCode.USERNAME_REGISTERED, "Username already registered");
        }

        final String userId = NanoIdUtils.randomNanoId();
        final Organization org = organizationService.create(userId);

        User user = User.builder()
                .email(registerRequest.getEmail())
                .fullName(registerRequest.getFullName())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();
        user.setId(userId);
        user.setOrganizationId(org.getId());
        return userRepository.save(user);
    }
}
