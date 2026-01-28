package com.yonsai.books.dto;

import javax.validation.constraints.*;

public record BookAddRequest (
        String category,

        @Size(min = 1, max = 100 , message = "제목은 1자 이상 100자 이하로 입력해주세요.")
        @NotBlank(message = "제목은 필수 입력값입니다.")
        String title,

        @Size(min = 1, max = 100 , message = "저자는 1자 이상 100자 이하로 입력해주세요.")
        @NotBlank(message = "저자는 필수 입력값입니다.")
        String author,

        @Size(min = 1, max = 200 , message = "설명은 1자 이상 200자 이하로 입력해주세요.")
        @NotBlank(message = "설명은 필수 입력값입니다.")
        String description,

        @Size(min = 1000 , message = "가격은 1000원 이상 입력해주세요.")
        @NotNull(message = "가격은 필수 입력값입니다.")
        Integer price,

        @Size(max = 100, message = "할인율은 100 이하로 입력해주세요.")
        @NotNull(message = "할인율은 필수 입력값입니다.")
        Integer discount,

        @Size(min = 10, message = "페이지는 10 이상 입력해주세요")
        @NotNull(message = "페이지는 필수 입력값입니다.")
        Integer pages
) {
}
