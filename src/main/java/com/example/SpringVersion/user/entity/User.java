package com.example.SpringVersion.user.entity;

import com.example.SpringVersion.user.dto.UserUpdateRequestDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="user_id")
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, unique=true)
    private String nickname;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String birthday;

    @Column(nullable = false)
    private String phoneNumber;

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private MemberRoleEnum role;

    @Builder
    public User(String email, String password, String username, String nickname,String gender, String birthday, String phoneNumber){
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;

    }

//    public static User of (String email, String password, String username, String nickname,String gender, String birthday, String phoneNumber) {
//        return User.builder()
//                .email(email)
//                .password(password)
//                .username(username)
//                .nickname(nickname)
//                .gender(gender)
//                .birthday(birthday)
//                .phoneNumber(phoneNumber)
//                .build();
//    }
    public void updateUsername(UserUpdateRequestDto requestDto) {
        if (requestDto.getUsername() != null) {
            this.username = requestDto.getUsername();
        }
    }
}
