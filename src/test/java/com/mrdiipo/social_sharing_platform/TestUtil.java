package com.mrdiipo.social_sharing_platform;

import com.mrdiipo.social_sharing_platform.user.User;

public class TestUtil {
     static User createValidUser() {
        User user = new User();
        user.setUsername("test_user");
        user.setDisplayName("test_display");
        user.setPassword("P4ss@word");
        user.setImage("profile-image.png");
        return user;
    }
}
