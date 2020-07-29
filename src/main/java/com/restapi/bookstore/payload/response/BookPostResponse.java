package com.restapi.bookstore.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class BookPostResponse {

    private String title;
    private String description;
    private String pages;
    private String author;
    private Set<String> categories;

    private Set<String> getCategories() {
        if(categories == null){
            return null;
        }

        return new HashSet<>(categories);
    }

    public void setCategories(Set<String> categories) {
        if(categories == null) {
            this.categories = null;
        }

        this.categories = Collections.unmodifiableSet(categories);
    }
}
