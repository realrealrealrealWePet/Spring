package com.example.SpringVersion.user.entity;

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

    private boolean isDeleted;

//    @Column(nullable = false)
//    @Enumerated(EnumType.STRING)
//    private MemberRoleEnum role;

    @Builder
    public User(String email, String password, String username, String nickname,String gender, String birthday, String phoneNumber, boolean isDeleted){
        this.email = email;
        this.password = password;
        this.username = username;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.isDeleted = isDeleted;
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
    public void setNickname(String nickname){
        this.nickname = nickname;
    }

    public void deleteUser() {
        this.isDeleted = true;
        this.email = this.email + "(withdrawal)";
        this.nickname = this.nickname + "(withdrawal)";
    }
}
