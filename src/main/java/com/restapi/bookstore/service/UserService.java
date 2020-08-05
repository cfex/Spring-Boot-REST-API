package com.restapi.bookstore.service;

import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.payload.response.*;
import com.restapi.bookstore.security.UserPrincipal;

public interface UserService {
    PageableResponse<User> listAll(int page, int size);
    LoggedUserResponse getCurrentUser(UserPrincipal currentUser);
    CredentialsAvailability checkUsernameAvailability(String username);
    CredentialsAvailability checkEmailAvailability(String email);
    UserProfileResponse showUserProfile(String username);
    User createUser(User user);
    User updateUser(User requestUser, String username, UserPrincipal currentUser);
    HttpResponse deleteUser(Long id, UserPrincipal currentUser);
}
