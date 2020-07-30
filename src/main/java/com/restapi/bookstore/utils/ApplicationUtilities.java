package com.restapi.bookstore.utils;

import java.util.Random;

public class ApplicationUtilities {

    public static void validateRequestPageAndSize(int page, int size) {
        if (page < 0 || size < 0) {
            throw new RuntimeException("Page/Size number cannot be les than 0");
        } else if (size > RequestConstants.MAX_PAGE_SIZE) {
            throw new RuntimeException("Ma page size exceeded");
        }
    }

    public static String generateISBN() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }
}
