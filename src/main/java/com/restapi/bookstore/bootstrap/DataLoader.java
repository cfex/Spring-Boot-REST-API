package com.restapi.bookstore.bootstrap;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.model.Cover;
import com.restapi.bookstore.model.User;
import com.restapi.bookstore.repository.BookRepository;
import com.restapi.bookstore.repository.CategoryRepository;
import com.restapi.bookstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

//        Category category = new Category();
//        category.setDescription("Testing");
//
//        categoryRepository.save(category);

        Book book1 = new Book();
        book1.setTitle("First book");
        book1.setDescription("First book for testing purpose");
        book1.setPages(112);
        book1.setCover(Cover.SOFT_COVER);
        book1.setAuthor("Nenad");

        Book book2 = new Book();
        book2.setTitle("Second book");
        book2.setDescription("Second book for testing purpose");
        book2.setPages(477);
        book2.setCover(Cover.SOFT_COVER);
        book2.setAuthor("Jovan");

        Book book3 = new Book();
        book3.setTitle("Third book");
        book3.setDescription("Third book for testing purpose");
        book3.setPages(321);
        book3.setCover(Cover.SOFT_COVER);
        book3.setAuthor("Katarina");

        Book book4 = new Book();
        book4.setTitle("Fourth book");
        book4.setDescription("Fourth book for testing purpose");
        book4.setPages(44);
        book4.setCover(Cover.SOFT_COVER);
        book4.setAuthor("Ivana");

//        book1.getCategories().add(category);
//        category.getBooks().add(book1);


        User user = new User();
        user.setFirstName("Nenad");
        user.setLastName("Jevtic");
        user.getBooks().add(book1);
        user.getBooks().add(book2);
        user.getBooks().add(book3);
        user.getBooks().add(book4);

        book1.setUser(user);
        book2.setUser(user);
        book3.setUser(user);
        book4.setUser(user);

        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4));

    }
}
