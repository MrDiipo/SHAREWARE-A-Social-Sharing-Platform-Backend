package com.mrdiipo.social_sharing_platform;

import com.mrdiipo.social_sharing_platform.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserController {

    @Autowired  TestRestTemplate testRestTemplate;

    @Test
    public void postUser_whenUserIsValid_receiveOk(){

        User user = new User();
        user.setUsername("test_user");
        user.setDisplayName("test_display");
        user.setPassword("password");

       ResponseEntity<Object> response = testRestTemplate.postForEntity("/api/1.0/users", user, Object.class);

       assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}
