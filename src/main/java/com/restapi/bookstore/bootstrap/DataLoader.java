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

import static com.restapi.bookstore.utils.ApplicationUtilities.generateISBN;

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

        Category category = Category.builder()
                .title("Programming")
                .build();

        User user = User.builder()
                .firstName("Nenad")
                .lastName("Jevtic")
                .userName("jevta")
                .email("jevtic.nenad.jevta@gmail.com")
                .build();

        Book book = Book.builder()
                .isbn(generateISBN())
                .title("First Book")
                .description("This is test for first book")
                .pages(221)
                .author("Nenad")
                .cover(Cover.SOFT_COVER)
                .user(user)
                .category(category)
                .build();


        userRepository.save(user);
        categoryRepository.save(category);
        bookRepository.save(book);

    }
}
