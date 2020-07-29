package com.restapi.bookstore.utils;

public class ApplicationUtilities {

    public static void validateRequestPageAndSize(int page, int size) {
        if (page < 0 || size < 0) {
            throw new RuntimeException("Page/Size number cannot be les than 0");
        } else if (size > RequestConstants.MAX_PAGE_SIZE) {
            throw new RuntimeException("Ma page size exceeded");
        }
    }
}
