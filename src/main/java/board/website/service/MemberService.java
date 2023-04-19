package board.website.service;
import board.website.model.Member;
import board.website.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor

public class MemberService {

    private MemberRepository memberRepository;

    public void joinMember(Member member) {
        memberRepository.joinMember(member);
    }

    public Member findMemberById(String id) {
        return memberRepository.findMemberById(id);
    }

}
