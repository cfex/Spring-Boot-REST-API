package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.role.Role;
import com.restapi.bookstore.model.role.RoleName;
import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.payload.request.RegisterUserRequest;
import com.restapi.bookstore.payload.response.*;
import com.restapi.bookstore.repository.RoleRepository;
import com.restapi.bookstore.repository.UserRepository;
import com.restapi.bookstore.security.UserPrincipal;
import com.restapi.bookstore.service.UserService;
import com.restapi.bookstore.utils.ApplicationUtilities;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.restapi.bookstore.utils.ApplicationConstants.CREATED_AT;
import static com.restapi.bookstore.utils.ApplicationUtilities.isUserAdmin;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public PageableResponse<User> listAll(int page, int size) {
        ApplicationUtilities.validateRequestPageAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, CREATED_AT);
        Page<User> users = userRepository.findAll(pageable);
        List<User> content = users.getNumberOfElements() == 0 ? Collections.emptyList() : users.getContent();

        return new PageableResponse<>(content,
                users.getNumber(),
                users.getSize(),
                users.getTotalElements(),
                users.getTotalPages());
    }

    @Override
    public LoggedUserResponse getCurrentUser(UserPrincipal currentUser) {

        return LoggedUserResponse.builder()
                .id(currentUser.getId())
                .firstName(currentUser.getFirstName())
                .lastName(currentUser.getLastName())
                .email(currentUser.getEmail())
                .build();
    }

    @Override
    public CredentialsAvailability checkUsernameAvailability(String username) {
        Boolean isAvailable = !userRepository.existsByUserName(username);

        return new CredentialsAvailability(isAvailable);
    }

    @Override
    public CredentialsAvailability checkEmailAvailability(String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);

        return new CredentialsAvailability(isAvailable);
    }

    @Override
    public UserProfileResponse showUserProfile(String username) {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new RuntimeException("No user found"));

        return UserProfileResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUserName())
                .email(user.getEmail())
                .address(user.getAddress())
                .books(user.getBooks())
                .build();
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        List<Role> roles = new ArrayList<>();

        roles.add(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(() -> new RuntimeException("Not Role found!")));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User requestUser, String username, UserPrincipal currentUser) {
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new RuntimeException("User with : " + username + " not found"));

        if (user.getUserName().equals(currentUser.getUsername())
                || isUserAdmin(currentUser)) {

            user.setFirstName(requestUser.getFirstName());
            user.setLastName(requestUser.getLastName());
            user.setUserName(requestUser.getUserName());
            user.setEmail(requestUser.getEmail());
            user.setPassword(passwordEncoder.encode(requestUser.getPassword()));

            return userRepository.save(user);

        }

        throw new RuntimeException("asdas");
    }

    @Override
    public HttpResponse registerUser(RegisterUserRequest requestUser) {
        if (userRepository.existsByUserName(requestUser.getUsername())
                || userRepository.existsByEmail(requestUser.getEmail())) {
            throw new RuntimeException("Credentials already taken!");
        }

        List<Role> roles = Collections.singletonList(roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("No role found!")));

        User user = User.builder()
                .firstName(requestUser.getFirstName())
                .lastName(requestUser.getLastName())
                .userName(requestUser.getUsername())
                .email(requestUser.getEmail())
                .password(passwordEncoder.encode(requestUser.getPassword()))
                .roles(roles)
                .build();

        userRepository.save(user);

        return new HttpResponse(Boolean.TRUE, "User registered successfully");
    }

    @Override
    public HttpResponse deleteUser(Long id, UserPrincipal currentUser) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No user found"));

        if (isUserAdmin(currentUser)) {
            userRepository.delete(user);
            return new HttpResponse(Boolean.TRUE, "You successfully removed USER: " + user.getUserName());
        }

        HttpResponse response = new HttpResponse(Boolean.FALSE, "You don't have permission to delete users!");
        throw new RuntimeException(response.getMessage());
    }
}
