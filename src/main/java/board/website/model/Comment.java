package board.website.model;

import lombok.Getter;
import lombok.Setter;


@Setter @Getter
public class Comment {

    private Integer commentId;
    private Integer memberId;
    private String nickname;
    private Integer boardId;
    private String content;
    private long createDate;
    private long modifyDate;
}
