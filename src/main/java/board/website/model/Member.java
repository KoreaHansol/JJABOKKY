package board.website.model;

import lombok.*;

import java.sql.Timestamp;

@Getter @Setter @Builder
public class Member {

    private Integer memberId;
    private String id;
    private String password;
    private String email;
    private String name;
    private String nickname;
    private String role;
    private Timestamp createDate;
    private Timestamp loginDate;
}
