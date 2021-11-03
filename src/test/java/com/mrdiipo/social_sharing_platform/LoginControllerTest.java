package com.mrdiipo.social_sharing_platform;

import com.mrdiipo.social_sharing_platform.error.ApiError;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class LoginControllerTest {

    public static final String API_1_0_LOGIN = "/api/1.0/login";

    @Autowired TestRestTemplate testRestTemplate;

    public <T>ResponseEntity<T> login(Class<T> responseType){
        return testRestTemplate.postForEntity(API_1_0_LOGIN, null, responseType);
    }

    private void authenticate() {
        testRestTemplate.getRestTemplate().getInterceptors().add(
                new BasicAuthenticationInterceptor("test-user", "P4ss@word"));
    }

    @Test public void postLogin_withoutUsersCredentials_receiveUnauthorized(){
        ResponseEntity<Object> response = login(Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test public void postLogin_withIncorrectCredentials_receiveUnauthorized(){
        authenticate();
        ResponseEntity<Object> response = login(Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

    @Test public void postLogin_withoutUserCredentials_receiveApiError() {
        ResponseEntity<ApiError> response = login(ApiError.class);
        assertThat(response.getBody().getUrl()).isEqualTo(API_1_0_LOGIN);
    }

    @Test public void postLogin_withoutUserCredentials_receiveApiErrorWithoutValidationErrors() {
        ResponseEntity<String> response = login(String.class);
        assertThat(response.getHeaders().containsKey("WWW-Authenticate")).isFalse();
    }

    @Test public void postLogin_withoutUserCredentials_receiveUnauthorizedWithoutWWWAuthenticationHeader() {
        authenticate();
        ResponseEntity<Object> response = login(Object.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }



}
