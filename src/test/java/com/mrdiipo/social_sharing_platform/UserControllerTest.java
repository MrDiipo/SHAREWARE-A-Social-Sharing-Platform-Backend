package com.mrdiipo.social_sharing_platform;

import com.mrdiipo.social_sharing_platform.user.User;
import com.mrdiipo.social_sharing_platform.user.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import com.mrdiipo.social_sharing_platform.shared.GenericResponse;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    public static final String API_1_0_USERS = "/api/1.0/users";
    @Autowired  TestRestTemplate testRestTemplate;
    @Autowired UserRepository userRepository;

    @Before
    public void  cleanUp(){
        userRepository.deleteAll();
    }

    private User getUser() {
        User user = new User();
        user.setUsername("test_user");
        user.setDisplayName("test_display");
        user.setPassword("password");
        return user;
    }

    @Test
    public void postUser_whenUserIsValid_receiveOk(){
        User user = getUser();
        ResponseEntity<Object> response = testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void postUser_whenUserIsValid_userSavedToDataBase(){
        User user = getUser();
        testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
        assertThat(userRepository.count()).isEqualTo(1L);
    }

    @Test
    public void postUser_whenUserIsValid_receiveSuccessMessage(){
        User user = getUser();
        ResponseEntity<GenericResponse> response = testRestTemplate.postForEntity(API_1_0_USERS, user, GenericResponse.class);
        assertThat(response.getBody().getMessage()).isNotNull();
    }

    @Test
    public void postUser_whenUserIsValid_passwordIsHashedToDatabase(){

        User user = getUser();
        testRestTemplate.postForEntity(API_1_0_USERS, user, Object.class);
        List<User> users = userRepository.findAll();
        User inDB = users.get(0);
        assertThat(inDB.getPassword()).isNotEqualTo(user.getPassword());
    }


}
