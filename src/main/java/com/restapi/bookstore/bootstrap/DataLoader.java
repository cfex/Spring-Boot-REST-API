package com.restapi.bookstore.bootstrap;

import com.restapi.bookstore.model.book.Book;
import com.restapi.bookstore.model.book.Cover;
import com.restapi.bookstore.model.category.Category;
import com.restapi.bookstore.model.role.Role;
import com.restapi.bookstore.model.role.RoleName;
import com.restapi.bookstore.model.user.Address;
import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

import static com.restapi.bookstore.utils.ApplicationUtilities.generateISBN;

@Profile({"default", "test"})
@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    //TODO set profiles

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        Role userRole = Role.builder()
            .name(RoleName.ROLE_USER)
                .build();

        roleRepository.save(userRole);

        Address address = Address.builder()
                .state("Serbia")
                .city("Sabac")
                .zipCode("15 000")
                .street("Kralja Petra prvog")
                .build();

        Role role = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow( () -> new RuntimeException("err"));

        User user = User.builder()
                .firstName("Nenad")
                .lastName("Jevtic")
                .userName("jevta")
                .email("jevtic.nenad.jevta@gmail.com")
                .password(passwordEncoder.encode("jevta123"))
                .roles(Collections.singletonList(role))
                .address(address)
                .build();

        userRepository.save(user);
        address.setUser(user);
        addressRepository.save(address);

        Category category = Category.builder()
                .title("Programming")
                .build();

        categoryRepository.save(category);

        Book book = Book.builder()
                .isbn(generateISBN())
                .title("First Book")
                .description("This is test for first book")
                .pages(221)
                .author("Nenad")
                .cover(Cover.SOFT_COVER)
                .category(category)
                .user(user)
                .build();

        bookRepository.save(book);

    }
}
