package com.example.SpringVersion.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private Long userId;

    @Column(nullable = true,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

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

    public User(String email, String password, String name, String nickname,String gender, String birthday, String phoneNumber){
        this.email = email;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.gender = gender;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;

    }
}
