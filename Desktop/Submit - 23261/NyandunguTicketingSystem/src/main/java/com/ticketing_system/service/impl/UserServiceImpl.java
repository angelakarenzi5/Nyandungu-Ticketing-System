package com.ticketing_system.service.impl;

import com.ticketing_system.model.CustomUserDetails;
import com.ticketing_system.model.User;
import com.ticketing_system.repo.UserRepo;
import com.ticketing_system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private  UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new CustomUserDetails(user);
    }


    @Override
    public User getCurrentUser() {
        // Get the Authentication object from the SecurityContextHolder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            // Check if the principal is a UserDetails object
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;

                // Find the user in the database based on the username
                return userRepo.findByUsername(userDetails.getUsername());
            }
        }

        return null;
    }

    @Override
    public User createUser(User user) {
        //check if user with same email exists
        User check = userRepo.findByUsername(user.getUsername());
        System.out.println("check");
        if(check != null){
            return null;
        }
        System.out.println("saving...");
        return userRepo.save(user);
    }

    @Override
    public User updateUser(User user) {
        User savedUser = userRepo.findByUsername(user.getUsername());
        if (savedUser != null) {
            savedUser.setFirstName(user.getFirstName());
            savedUser.setLastName(user.getLastName());
            savedUser.setAge(user.getAge());
            savedUser.setPassword(user.getPassword());
            savedUser.setAddress(user.getAddress());
            savedUser.setPhoneNumber(user.getPhoneNumber());
            return userRepo.save(savedUser);
        }
        return null;
    }


    @Override
    public User authenticateUser(User user) {
        User savedUser = userRepo.findByUsername(user.getUsername());
        if(savedUser != null){
            if(savedUser.getPassword().equals(user.getPassword())){
                return savedUser;
            }
            return null;
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepo.findAll();
        /*if(search != null){
            List<User> filtered = new ArrayList<>();
            for (User user : users){
                if(user.getFirstName().equals(search) || user.getLastName().equals(search)){
                    filtered.add(user);
                }
                return filtered;
            }
        }*/
        return users;
    }

    @Override
    public User getUserById(Long userId) {
        return userRepo.findById(userId).orElse(null);
    }


}
