package com.mrdiipo.social_sharing_platform.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

     UserRepository userRepository;

//     Constructor injection makes it easy for testing
     public UserService(UserRepository userRepository){
         super();
         this.userRepository = userRepository;
     }

     public User save(User user){
         return userRepository.save(user);
     }
}
