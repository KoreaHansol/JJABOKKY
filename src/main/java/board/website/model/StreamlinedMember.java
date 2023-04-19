package board.website.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter @Setter @Builder
public class StreamlinedMember {
    private String id;
    private String email;
    private String name;
    private String nickname;
    private String role;
    private Timestamp loginDate;
}
