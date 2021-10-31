package com.mrdiipo.social_sharing_platform.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

     UserRepository userRepository;

     BCryptPasswordEncoder bCryptPasswordEncoder;

//     Constructor injection makes it easy for testing
     public UserService(UserRepository userRepository){
         super();
         this.userRepository = userRepository;
         this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
     }

     public User save(User user){
         user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         return userRepository.save(user);
     }
}
