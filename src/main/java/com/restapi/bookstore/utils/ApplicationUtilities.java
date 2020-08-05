package com.restapi.bookstore.utils;

import com.restapi.bookstore.model.role.RoleName;
import com.restapi.bookstore.security.CurrentlyLogged;
import com.restapi.bookstore.security.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Random;

public class ApplicationUtilities {

    public static void validateRequestPageAndSize(int page, int size) {
        if (page < 0 || size < 0) {
            throw new RuntimeException("Page/Size number cannot be les than 0");
        } else if (size > RequestConstants.MAX_PAGE_SIZE) {
            throw new RuntimeException("Max page size exceeded");
        }
    }

    /*
        Generate random 6-digit STRING to simulate ISBN
     */
    public static String generateISBN() {
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        return String.format("%06d", number);
    }

    /*
        Check if user have ADMIN role. If so, return TRUE, else return FALSE
     */
    public static boolean isUserAdmin(@CurrentlyLogged UserPrincipal userPrincipal) {
        return userPrincipal.getAuthorities().contains(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN.toString()));
    }
}
