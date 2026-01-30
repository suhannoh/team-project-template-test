package com.yonsai.books.dto;

import javax.validation.constraints.*;

/**
 * 책 수정 요청 DTO
 *  - @Valid 를 통해 유효성 검사를 진행한다.
 *  - 해당 DTO는 Book 엔티티와 매핑한다.
 * @author 노수한
 */
public record BookUpdateRequest(
        @NotBlank(message = "카테고리는 필수 입력값입니다.")
        //todo : 카테고리를 enum으로 리스트 생성
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

        @Min(value = 1000, message = "가격은 1000원 이상 입력해주세요.")
        @NotNull(message = "가격은 필수 입력값입니다.")
        Integer price,

        @Max(value = 100 , message = "할인율은 100 이하로 입력해주세요.")
        @NotNull(message = "할인율은 필수 입력값입니다.")
        Integer discount,

        @Min(value = 10, message = "페이지는 10장 이상 입력해주세요")
        @NotNull(message = "페이지는 필수 입력값입니다.")
        Integer pages,

        @Min(value = 1 , message = "재고는 1개 이상이여야 등록 가능합니다.")
        @NotNull(message = "재고 수량은 필수 입력값입니다.")
        Integer stock
) {
}
