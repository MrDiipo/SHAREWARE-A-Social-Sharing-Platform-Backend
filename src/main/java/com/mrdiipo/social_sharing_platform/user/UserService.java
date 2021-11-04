package com.mrdiipo.social_sharing_platform.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

     UserRepository userRepository;

     PasswordEncoder passwordEncoder;
//     Constructor injection makes it easy for testing
     public UserService(UserRepository userRepository,  PasswordEncoder passwordEncoder){
         super();
         this.userRepository = userRepository;
         this.passwordEncoder = passwordEncoder;
     }

     public User save(User user){
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         return userRepository.save(user);
     }
}
