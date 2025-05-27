package org.example.restful.service;

import org.example.restful.dto.UserDTO;
import org.example.restful.entity.User;
import org.example.restful.exception.UserRegistrationException;
import org.example.restful.repository.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }

    public User registerUser(String username, String password, String fullName, String email) {
        if (userRepository.existsByUsername(username)) {
            throw new UserRegistrationException("Username already taken");
        }

        if (userRepository.existsByEmail(email)) {
            throw new UserRegistrationException("Email already in use");
        }

        User user = new User(username, passwordEncoder.encode(password), fullName, email);
        return userRepository.save(user);
    }

    public UserDTO getUserDTOFromUser(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getFullName(), user.getEmail());
    }

    public User getCurrentUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
