package com.restapi.bookstore.bootstrap;

import com.restapi.bookstore.model.Book;
import com.restapi.bookstore.model.Category;
import com.restapi.bookstore.model.Cover;
import com.restapi.bookstore.model.User;
import com.restapi.bookstore.repository.BookRepository;
import com.restapi.bookstore.repository.CategoryRepository;
import com.restapi.bookstore.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import org.springframework.stereotype.Component;


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

//        book1.getCategories().add(category);
//        category.getBooks().add(book1);


        User user = new User();
        user.setFirstName("Nenad");
        user.setLastName("Jevtic");
        user.getBooks().add(book1);

        book1.setUser(user);

        userRepository.save(user);
        bookRepository.save(book1);


    }
}
