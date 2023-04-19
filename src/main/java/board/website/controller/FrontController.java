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
    public String hello() {
        return "안녕하세요";
    }

}
