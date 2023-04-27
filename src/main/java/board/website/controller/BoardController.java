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
        return boardRepository.getPostList(boardType, subBoardType);
    }

    @GetMapping("/getAllPostList")
    public ArrayList<Board> getAllPostList(@RequestParam String boardType) {
        return boardRepository.getAllPostList(boardType);
    }

    @GetMapping("/getPost/{boardId}")
    public Board getPost(@PathVariable Integer boardId) {
        Board board = boardRepository.getPost(boardId);
        System.out.println(board.getBoardId());
        System.out.println(board.getViews());

        if(board.getViews()==null) {
            boardRepository.updateViews(1, boardId);
        } else {
            boardRepository.updateViews(board.getViews() + 1, boardId);
        }

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
