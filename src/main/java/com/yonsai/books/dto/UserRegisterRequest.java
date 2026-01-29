package com.yonsai.books.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record UserRegisterRequest(
        @NotBlank(message = "이메일은 필수 입력값입니다.")
        String email,
        @NotBlank(message = "비밀번호는 필수 입력값입니다.")
        @Size(min = 8, max = 100 , message = "비밀번호는 8자 이상 100자 이하로 입력해주세요.")
        String password,
        @NotBlank(message = "이름은 필수 입력값입니다.")
        String name
) {
}
