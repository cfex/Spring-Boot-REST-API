package com.restapi.bookstore.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CredentialsAvailability {

    private Boolean available;
}
