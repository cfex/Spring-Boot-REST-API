package com.restapi.bookstore.bootstrap;

import com.restapi.bookstore.model.book.Book;
import com.restapi.bookstore.model.book.Cover;
import com.restapi.bookstore.model.category.Category;
import com.restapi.bookstore.model.user.User;
import com.restapi.bookstore.repository.BookRepository;
import com.restapi.bookstore.repository.CategoryRepository;
import com.restapi.bookstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Profile({"default", "test"})
@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    //TODO set profiles

    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {

        Category category1 = new Category();
        category1.setTitle("Testing");

        Category category2 = new Category();
        category2.setTitle("Programming");

        Book book1 = new Book();
        book1.setTitle("First book");
        book1.setDescription("First book for testing purpose");
        book1.setPages(112);
        book1.setCover(Cover.SOFT_COVER);
        book1.setAuthor("Nenad");
        book1.getCategories().add(category1);

        Book book2 = new Book();
        book2.setTitle("Second book");
        book2.setDescription("Second book for testing purpose");
        book2.setPages(477);
        book2.setCover(Cover.SOFT_COVER);
        book2.setAuthor("Jovan");
        book2.getCategories().add(category1);

        Book book3 = new Book();
        book3.setTitle("Third book");
        book3.setDescription("Third book for testing purpose");
        book3.setPages(321);
        book3.setCover(Cover.SOFT_COVER);
        book3.setAuthor("Katarina");
        book3.getCategories().add(category2);

        Book book4 = new Book();
        book4.setTitle("Fourth book");
        book4.setDescription("Fourth book for testing purpose");
        book4.setPages(44);
        book4.setCover(Cover.SOFT_COVER);
        book4.setAuthor("Ivana");
        book4.getCategories().add(category2);

        User user = new User();
        user.setFirstName("Nenad");
        user.setLastName("Jevtic");
        user.setUserName("jevta");
        user.setEmail("jevtic.nenad.jevta@gmail.com");
        user.getBooks().add(book1);
        user.getBooks().add(book2);
        user.getBooks().add(book3);
        user.getBooks().add(book4);

        book1.setUser(user);
        book2.setUser(user);
        book3.setUser(user);
        book4.setUser(user);

        userRepository.save(user);
        categoryRepository.saveAll(Arrays.asList(category1, category2));
        bookRepository.saveAll(Arrays.asList(book1, book2, book3, book4));


    }
}
