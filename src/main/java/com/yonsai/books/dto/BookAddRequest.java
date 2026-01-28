package com.yonsai.books.dto;

import javax.validation.constraints.*;

public record BookAddRequest (
        String category,

        @Size(min = 1, max = 100)
        @NotBlank
        String title,

        @Size(min = 1, max = 100)
        @NotBlank
        String author,

        @Size(min = 1, max = 200)
        @NotBlank
        String description,

        @Min(1000)
        @NotNull
        Integer price,

        @Max(100)
        @NotNull
        Integer discount,

        @Min(10)
        @NotNull
        Integer pages
) {
}
