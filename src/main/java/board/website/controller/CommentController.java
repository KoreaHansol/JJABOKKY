package board.website.controller;

import board.website.model.Comment;
import board.website.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@AllArgsConstructor
@RestController
@RequestMapping("/comment")
public class CommentController {

    CommentRepository commentRepository;
    @PostMapping("/saveComment")
    public Comment saveComment(@RequestBody Comment comment) {
        comment.setCreateDate(System.currentTimeMillis());
        commentRepository.saveComment(comment);
        return comment;
    }

    @GetMapping("/getCommentList")
    public ArrayList<Comment> getCommentList(@RequestParam String boardId) {
        System.out.println("boardId" + boardId);
        return commentRepository.getCommentList(boardId);
    }

}
