package board.website.controller;

import board.website.model.Member;
import board.website.security.TokenProvider;
import board.website.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.Timestamp;

@Slf4j
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/join")
    public ResponseEntity<?> memberJoin(@RequestBody Member member) {
        Member findMember = memberService.findMemberById(member.getId());

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/member/join"));
            if(findMember.getId().equals(member.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            if(findMember.getNickname().equals(member.getNickname())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        } catch(NullPointerException e) {
            log.info("등록된 조회");
        }

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        member.setRole("Normal");
        member.setCreateDate(new Timestamp(System.currentTimeMillis()));
        member.setLoginDate(new Timestamp(System.currentTimeMillis()));

        memberService.joinMember(member);

        return ResponseEntity.status(200).body(member);
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("id") String id, @RequestParam("password") String password) {
        Member memberById = memberService.findMemberById(id);
        if(memberById == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        boolean matches = bCryptPasswordEncoder.matches(password, memberById.getPassword());
        if(!matches) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Integer memberId = memberById.getMemberId();
        String token = tokenProvider.createToken(memberById, (1000 * 60 * 15));
        return ResponseEntity.status(HttpStatus.OK).body(token);
    }



//    @GetMapping("/token")
//    public Map<String, Object> createToken(@RequestParam(value = "subject") String subject) {
//        String token = securityService.createToken(subject, (2*1000*60));
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("result", token);
//        return map;
//    }
//
//    @GetMapping("getsubject")
//    public Map<String, Object> getSubject(@RequestParam(value = "token") String token) {
//        String subject = securityService.getSubject(token);
//        Map<String, Object> map = new LinkedHashMap<>();
//        map.put("result", subject);
//        return map;
//    }

//    @PostMapping("/doLogin")
//    public ResponseEntity<Object> login(@RequestBody Member member) throws NullPointerException {
//        System.out.println("doLogin 하라는 사인");
//        Member findMember = memberService.findMemberById(member.getId());
//        if(findMember == null) {
//            return ResponseEntity.status(404).body("회원이 없습니다.");
//        }
//
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//
//        boolean matches = bCryptPasswordEncoder.matches(member.getPassword(), findMember.getPassword());
//
//        if(matches) {
//            return ResponseEntity.status(HttpStatus.OK).body(member);
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("비밀번호가 다릅니다");
//        }
//    }
}
