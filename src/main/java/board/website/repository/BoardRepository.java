package board.website.repository;

import board.website.model.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface BoardRepository {
    ArrayList<Board> getPostList(@Param("boardType") String boardType, @Param("subBoardType") String subBoardType);
    void savePost(@Param("board") Board board);
    Board getPost(@Param("boardId") String boardId);
}
