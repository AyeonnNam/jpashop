package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
   // @Rollback(false) -> 눈으로 확인하는 방법 (DB)
    public void 회원가입() throws Exception{

        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long memberId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(memberId));

    }




    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외()throws Exception{

        //given
    Member member1 =  new Member();
    member1.setName("kim1");


    Member member2 =  new Member();
    member2.setName("kim1");

        //when
    memberService.join(member1);
//    try {memberService.join(member2);
//    } catch(IllegalStateException e){
//        return;
//    }
       memberService.join(member2);

        //then
      fail("예외가 발생해야 한다.");

    }

}