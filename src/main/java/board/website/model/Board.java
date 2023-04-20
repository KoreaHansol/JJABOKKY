package board.website.model;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class Board {
    private int boardId;
    private int memberId;
    private String boardType;
    private String subBoardType;
    private String title;
    private String content;
    private String createDate;
    private String modifyDate;
}
