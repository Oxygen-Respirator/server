package com.oxygen.oxygenspring.db.entity;

import com.oxygen.oxygenspring.db.entity.utils.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Users extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false, unique = true)
    private String userId;

    @Column(name = "user_pw", nullable = false)
    private String userPw;

    @Column(name = "user_nickname", nullable = false)
    private String userNickname;

    @Builder
    public Users(String userId, String userPw, String userNickname) {
        this.userId = userId;
        this.userPw = userPw;
        this.userNickname = userNickname;
    }
}
