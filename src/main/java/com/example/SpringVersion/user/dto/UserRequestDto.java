package com.example.SpringVersion.user.dto;

import lombok.*;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UserRequestDto {
    //유효성 검사
    @NotBlank(message ="이메일을 입력해주세요.")
    private String email;
    @NotBlank(message = "비밀번호를 입력해 주세요.")
    private String password;
    @NotBlank(message = "이름을 입력해 주세요.")
    @Size(min = 2, max = 5, message = "사용자 이름은 최소 2글자 최대 5글자로 구성되어야합니다.")
    private String name;
    //닉네임 형식
    //2자 이상 16자 이하, 영어 또는 숫자 또는 한글로 구성해야 하며 / 한글 초성 및 모음은 허가하지 않음
    @NotBlank(message = "닉네임을 입력해 주세요.")
    @Pattern(regexp = "^(?=.*[a-z0-9가-힣])[a-z0-9가-힣]{2,16}$", message =  "닉네임은 영어, 숫자, 한글로 구성되어야하며 최소 2글자 최대 16글자로 구성되어야합니다.")
    private String nickname;
    @Enumerated(EnumType.STRING)
    private String gender;

    //생년월일 형식
    // 년도는 19, 2x로 시작하며 그후에 0 ~ 9 중에 하나로 나올수도 있으며 / 월은 십의 자리가 0일 떄는 일의 자리는 0 ~ 9로 구성되며 /
    // 십의 자리가 1일떄는 일의 자리는 0 ~ 2로 구성되어야 하며 / 일은 십의자리가 0 ~ 2일 떄는 일의 자리는 0 ~ 9로 구성되며 / 십의자리가 3일 떄는 일의 자리는 0~1로 구성되며 /
    // 8자리 생년월일로 입력해야 함
    @NotBlank(message = "생년월일을 입력해 주세요.")
    @Pattern(regexp = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$", message = "생년월일 8자리 형식에 맞지 않습니다.")
    private String birthday;
    @NotBlank(message = "핸드폰 번호를 입력해 주세요.")
    @Pattern(regexp = "^\\d{1,11}$", message = "'-'를 제외한 숫자만 입력해주세요.")
    private String phoneNumber;
}
