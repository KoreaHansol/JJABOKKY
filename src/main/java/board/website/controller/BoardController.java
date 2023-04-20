package board.website.controller;

import board.website.model.Board;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/board")
public class BoardController {
    @PostMapping("/save")
    public void saveWrite(@RequestBody Board board) {
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }

    @PostMapping("/save/images")
    public void saveImageUrls(@RequestBody Board board) {
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
    }
}
