package com.mrdiipo.social_sharing_platform;

import com.mrdiipo.social_sharing_platform.user.User;
import com.mrdiipo.social_sharing_platform.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired TestEntityManager testEntityManager;
    @Autowired UserRepository userRepository;

    @Test
    public void findByUsername_whenUserExists_returnUser(){

        User user = new User();
        user.setUsername("test-user");
        user.setDisplayName("test-display");
        user.setPassword("P4ss@word");

        testEntityManager.persist(user);
        User inDB = userRepository.findByUsername("test-user");
        assertThat(inDB).isNotNull();
    }

    @Test
    public void findByUsername_whenUserDoesNotExist_returnsNull(){
        User inDB = userRepository.findByUsername("nonexistinguser");
        assertThat(inDB).isNull();
    }
}
