package board.website.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Board {
    private Integer boardId;
    private Integer memberId;
    private String nickname;
    private String boardType;
    private String subBoardType;
    private String title;
    private String content;
    private Integer commentCount;
    private long createDate;
    private long modifyDate;
    private Integer views;
}
