package board.website.repository;

import board.website.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface CommentRepository {
    void saveComment(@Param("comment") Comment comment);
    ArrayList<Comment> getCommentList(@Param("boardId") String boardId);
}
