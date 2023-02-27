package board.crud.service;

import board.crud.dto.MemberDTO;
import board.crud.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

    //given
    //when
    //then

@SpringBootTest
@Transactional
@ExtendWith(SpringExtension.class)
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입")
    void join() throws Exception {
        //given
        //form 에서 넘어온 것을 컨트롤러에서 DTO로 서비스에서 통신.
        MemberDTO memberDTO = new MemberDTO("Jung", "Devil", "user1", "1q2w3e!");
        //when
        Long saveId = memberService.join(memberDTO);
        //then
        assertEquals("Jung", memberRepository.findByMid(saveId).get().getName());
    }

    @Test
    @DisplayName("회원가입시 중복 테스트")
    void duplicate() throws Exception{
        //given
        MemberDTO memberDTO1 = new MemberDTO("Kim", "KKK", "user1", "password");
        MemberDTO memberDTO2 = new MemberDTO("Jung", "KKK", "user2", "password");
        MemberDTO memberDTO3 = new MemberDTO("Park", "QQQ", "user1", "password");
        //when
        memberService.join(memberDTO1);
        //then
        assertThrows(IllegalStateException.class, () ->{
            memberService.join(memberDTO2);
        });

        assertThrows(IllegalStateException.class, () ->{
            memberService.join(memberDTO3);
        });
    }

}