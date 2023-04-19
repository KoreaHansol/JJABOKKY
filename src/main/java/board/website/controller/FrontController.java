package board.website.controller;

import board.website.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/")
public class FrontController {

    private MemberRepository memberRepository;

    @GetMapping("/getInitData")
    public String getBoardList() {
        return "나중에 추가될 4가지 게시판의 목록들";
    }

}
