package jpabook.jpashop.service;


import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member){
    validateDuplicateNumber(member);
    memberRepository.save(member);
    return member.getId();
}

    private void validateDuplicateNumber(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    //회원 조회

    public List<Member> findMembers(){
            return memberRepository.findAll();
    }



    public Member findOne(Long memberId){
            return memberRepository.findOne(memberId);
    }


}
