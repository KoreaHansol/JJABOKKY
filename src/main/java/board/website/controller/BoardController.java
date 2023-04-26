package board.website.controller;

import board.website.model.Board;
import board.website.repository.BoardRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@AllArgsConstructor
@RequestMapping("/board")
public class BoardController {
    BoardRepository boardRepository;

    @GetMapping("/getPostList/{boardType}/{subBoardType}")
    public ArrayList<Board> getPostList(@PathVariable String boardType, @PathVariable String subBoardType) {
        ArrayList<Board> postList = boardRepository.getPostList(boardType, subBoardType);
        return postList;
    }

    @GetMapping("/getPost/{boardId}")
    public Board getPost(@PathVariable String boardId) {
        return boardRepository.getPost(boardId);
    }


    @PostMapping("/save")
    public void savePost(@RequestBody Board board) {
        board.setCreateDate(System.currentTimeMillis());
        boardRepository.savePost(board);
    }

    @PostMapping("/save/images")
    public void saveImageUrls(@RequestBody ArrayList<String> imageUrls) {

    }
}
