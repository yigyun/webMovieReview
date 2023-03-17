package board.web.service;

import board.crud.domain.member.Member;
import board.crud.dto.MemberDTO;
import board.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(MemberDTO memberDTO) {
        if(memberRepository.existsByNick(memberDTO.getNick())){
            throw new IllegalStateException("닉네임이 중복입니다.");
        }else if(memberRepository.existsByUsername(memberDTO.getUsername())){
            throw new IllegalStateException("아이디가 중복입니다.");
        }

        Member member = Member.builder()
                .username(memberDTO.getUsername())
                .nick(memberDTO.getNick())
                .password(memberDTO.getPassword())
                .name(memberDTO.getName())
                .build();

        try{
            memberRepository.save(member);
        } catch (Exception e){
            e.printStackTrace();
        }

        return member.getId();
    }
}
