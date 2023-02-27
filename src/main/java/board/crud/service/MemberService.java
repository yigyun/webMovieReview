package board.crud.service;

import board.crud.exception.CustomValidationException;
import board.crud.member.Member;
import board.crud.dto.MemberDTO;
import board.crud.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long join(MemberDTO memberDTO) {
        if(memberRepository.existsByNick(memberDTO.getNick())){
            throw new IllegalStateException("닉네임이 중복입니다.");
        }else if(memberRepository.existsByUsername(memberDTO.getUsername())){
            throw new IllegalStateException("아이디가 중복입니다.");
        }
        Member member = memberDTO.toEntity();

        memberRepository.save(member);
        return member.getMid();
    }
}
