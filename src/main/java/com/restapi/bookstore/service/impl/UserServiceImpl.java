package com.restapi.bookstore.service.impl;

import com.restapi.bookstore.model.role.Role;
import com.restapi.bookstore.model.role.RoleName;
import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.payload.response.CredentialsAvailability;
import com.restapi.bookstore.payload.response.LoggedUserResponse;
import com.restapi.bookstore.payload.response.UserProfileResponse;
import com.restapi.bookstore.repository.RoleRepository;
import com.restapi.bookstore.repository.UserRepository;
import com.restapi.bookstore.security.UserPrincipal;
import com.restapi.bookstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class UserServiceImpl  implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoggedUserResponse getCurrentUser(UserPrincipal userPrincipal) {

        return LoggedUserResponse.builder()
                .firstName(userPrincipal.getFirstName())
                .lastName(userPrincipal.getLastName())
                .email(userPrincipal.getEmail())
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
        if(userRepository.existsByUserName(user.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Email already exists");
        }

        List<Role> roles = new ArrayList<>();

        roles.add(roleRepository.findByName(RoleName.ROLE_USER).orElseThrow( () -> new RuntimeException("Not Role found!")));
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public User updateUser(User requestUser, String username, UserPrincipal currentUserPrincipal) {
        User user = userRepository.findByUserName(username).orElseThrow(
                () -> new RuntimeException("User with : " + username + " not found"));

        if(user.getUserName().equals(currentUserPrincipal.getUsername())
                || currentUserPrincipal.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()))){

                user.setFirstName(requestUser.getFirstName());
                user.setLastName(requestUser.getLastName());
                user.setUserName(requestUser.getUserName());
                user.setEmail(requestUser.getEmail());
                user.setPassword(passwordEncoder.encode(requestUser.getPassword()));

                return userRepository.save(user);

        }

        throw new RuntimeException("asdas");
    }
}
