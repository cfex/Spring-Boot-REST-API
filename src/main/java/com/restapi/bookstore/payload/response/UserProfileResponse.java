package com.restapi.bookstore.payload.response;


import com.restapi.bookstore.model.book.Book;
import com.restapi.bookstore.model.user.Address;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserProfileResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private Address address;
    private List<Book> books;
}
