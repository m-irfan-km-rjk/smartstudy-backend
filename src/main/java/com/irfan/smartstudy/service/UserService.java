package com.irfan.smartstudy.service;

import com.irfan.smartstudy.dto.SignupRequest;
import com.irfan.smartstudy.model.User;
import com.irfan.smartstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found."));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(SignupRequest request) {
        User user = new User();
        user.setName(request.username);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));

        return userRepository.save(user);
    }

    public User updateUser(User user) {
        if (user.getId() == null) {
            throw new RuntimeException("User ID required");
        }

        User existing = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getName() != null)
            existing.setName(user.getName());

        if (user.getEmail() != null)
            existing.setEmail(user.getEmail());

        if (user.getPassword() != null)
            existing.setPassword(user.getPassword());

        return userRepository.save(existing);
    }

    public void deleteUserById(Long id) {
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User not found.");
        }
        userRepository.deleteById(id);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}
