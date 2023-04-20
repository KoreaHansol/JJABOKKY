package board.website.controller;

import board.website.model.Member;
import board.website.repository.MemberRepository;
import board.website.security.TokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenProvider tokenProvider;

    MemberRepository memberRepository;

    @GetMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestParam("id") String id) {
        Member memberById = memberRepository.findMemberById(id);
        if(memberById == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("아이디를 찾을수 없습니다.");
        }

        String token = tokenProvider.createToken(memberById, (1000 * 60 * 60));
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }
}
