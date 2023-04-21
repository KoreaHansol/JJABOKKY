package board.website.repository;

import board.website.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface MemberRepository {
//    ArrayList<Member> getAllMembers();

    void joinMember(@Param("member") Member member);

    Member findMemberById(@Param("id") String id);

}