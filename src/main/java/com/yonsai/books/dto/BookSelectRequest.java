package com.yonsai.books.dto;

import com.yonsai.books.domain.SellStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * 도서 검색 키워드 요청 DTO
 *
 * @param keyword
 * @param category
 * @param price
 * @param status
 * @param author
 *
 * @author 노수한
 */
public record BookSelectRequest(
        @Size(min = 1 , max = 100 , message = "제목 키워드는 1자 이상 100자 이하로 입력해주세요")
        String keyword,
        String category,
        @Min(value = 1000 , message = "가격은 최소 1000원 이상 입력해주세요")
        Integer price,
        SellStatus status,
        @Size(min = 1 , max = 100 , message = "저자는 1자 이상 100자 이하로 입력해주세요")
        String author
) {
}
