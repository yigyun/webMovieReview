package board.crud.controller;

import board.crud.dto.MemberDTO;
import board.crud.member.Member;
import board.crud.member.MemberForm;
import board.crud.service.MemberService;
import board.crud.service.UserSecurityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserSecurityService userSecurityService;

    @Autowired
    PasswordEncoder passwordEncoder;
// 회원가입 테스트


    MemberDTO register() {
        MemberForm memberForm1 = new MemberForm();
        memberForm1.setName("Kim");
        memberForm1.setNick("QQQ");
        memberForm1.setUsername("user1");
        memberForm1.setPassword("password");
        MemberDTO memberDTO = new MemberDTO(memberForm1.getName(), memberForm1.getNick(), memberForm1.getUsername(), memberForm1.getPassword());
        return memberDTO;
    }
// 컨트롤러 로그인 테스트
    @Test
    @DisplayName("로그인 성공 테스트")
    void signUpSuccess() {
        Member member = register().toEntity();
        memberService.join(register());

    }

    @Test
    void loginId() {
    }
}