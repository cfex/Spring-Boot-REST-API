package com.restapi.bookstore.service;

import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.payload.response.CredentialsAvailability;
import com.restapi.bookstore.payload.response.LoggedUserResponse;
import com.restapi.bookstore.payload.response.UserProfileResponse;
import com.restapi.bookstore.security.UserPrincipal;

public interface UserService {
    LoggedUserResponse getCurrentUser(UserPrincipal userPrincipal);
    CredentialsAvailability checkUsernameAvailability(String username);
    CredentialsAvailability checkEmailAvailability(String email);
    UserProfileResponse showUserProfile(String username);
    User createUser(User user);
    User updateUser(User requestUser, String username, UserPrincipal currentUserPrincipal);
}
