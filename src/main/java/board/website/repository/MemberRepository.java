package board.website.repository;

import board.website.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberRepository {
    List<Member> getAllMembers();

    void joinMember(@Param("member") Member member);

    Member findMemberById(@Param("id") String id);

}