package com.restapi.bookstore.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PageableResponse<T> {

    private List<T> content;
    private int page;
    private int size;
    private long elementCount;
    private int pagesCount;

    public List<T> getContent() {
        if (content == null) {
            return null;
        }

        return new ArrayList<>(content);
    }

    public final void setContent(List<T> content) {
        if (content == null) {
            this.content = null;
        } else {
            this.content = Collections.unmodifiableList(content);
        }
    }
}
