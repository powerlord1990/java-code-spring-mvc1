package com.company.service;

import com.company.entity.Order;
import com.company.entity.User;
import com.company.exception.UserNotFoundException;
import com.company.repository.OrderRepository;
import com.company.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public User createUser(User user) {
        User savedUser = userRepository.save(user);
        for (Order order : savedUser.getOrders()) {
            order.setUser(savedUser);
            orderRepository.save(order);
        }
        return savedUser;
    }

    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
